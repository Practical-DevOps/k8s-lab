package academy.softserve.message

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(DemoProperties::class)
class MessageServiceApplication

fun main(args: Array<String>) {
	runApplication<MessageServiceApplication>(*args)
}