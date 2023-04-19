package academy.softserve.ui

import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.client.postForObject
import java.text.SimpleDateFormat
import java.util.*

class GuestbookService(private val restTemplate: RestTemplate, private val endpoint: String) {

    fun add(username: String, message: String): Map<String, String> {
        val payload = mutableMapOf<String, String>()
        payload["username"] = username
        payload["message"] = message
        payload["timestamp"] = dateFormat.format(Date())
        return restTemplate.postForObject(endpoint, payload, Map::class)
    }

    fun all(): List<Map<*, *>> =
            try {
                val response = restTemplate.getForObject<Map<String, Any>>(endpoint)
                val embedded = response["_embedded"] as Map<*, *>
                val messages = embedded["messages"] as List<*>
                messages
                        .asSequence()
                        .filter { message ->
                            (message as Map<*, *>).containsKey("_links")
                        }
                        .map { message ->
                            (message as Map<*, *>)["_links"] as Map<*, *>
                        }
                        .filter { links -> links.containsKey("self") }
                        .map { links -> links["self"] as Map<*, *> }
                        .map { self -> self["href"] as String }
                        .map { href -> restTemplate.getForObject<Map<*, *>>(href) }
                        .toList()
            } catch (e: HttpStatusCodeException) {
                allDefault()
            }

    private fun allDefault(): List<Map<*, *>> = listOf(
            mapOf(
                    "username" to "system",
                    "message" to "Guestbook Service is currenctly unavailable"
            )
    )

    companion object {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    }
}