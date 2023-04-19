package academy.softserve.guestbook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class GuestbookServiceApplication

fun main(args: Array<String>) {
	runApplication<GuestbookServiceApplication>(*args)
}
