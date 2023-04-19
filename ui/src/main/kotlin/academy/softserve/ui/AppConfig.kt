package academy.softserve.ui

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.web.client.RestTemplate

@Configuration
@EnableRedisHttpSession
class AppConfig {

    @Value("#{systemEnvironment['MESSAGE_HOST']}")
    private lateinit var messageServiceEndpoint: String

    @Value("#{systemEnvironment['GUESTBOOK_HOST']}")
    private lateinit var guestbookServiceEndpoint: String

    @Bean
    fun restTemplate() = RestTemplate()

    @Bean
    fun messageService(restTemplate: RestTemplate): MessageService {
        val endpoint = "${messageServiceEndpoint}/hello"
        return MessageService(restTemplate, endpoint)
    }

    @Bean
    fun guestbookService(restTemplate: RestTemplate): GuestbookService {
        val endpoint = "${guestbookServiceEndpoint}/api/messages"
        return GuestbookService(restTemplate, endpoint)
    }
}