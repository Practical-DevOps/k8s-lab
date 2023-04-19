package academy.softserve.ui

import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

class MessageService(private val restTemplate: RestTemplate, private val endpoint: String) {
    fun message(name: String): Map<String, String> =
            try {
                restTemplate.getForObject("$endpoint/$name")
            } catch (e: HttpStatusCodeException) {
                defaultMessage()
            }

    private fun defaultMessage(): Map<String, String> = mapOf("greeting" to "Unable to connect")
}