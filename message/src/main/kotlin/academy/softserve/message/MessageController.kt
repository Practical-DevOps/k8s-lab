package academy.softserve.message

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(private val properties: DemoProperties) {
	/**
	 * Flag for throwing a 503 when enabled
	 */
	private var misbehave = false

	private val hostname: String
		get() = System
			.getenv()
			.getOrDefault("HOSTNAME", "unknown")

	private val logger: Logger = LoggerFactory.getLogger(MessageController::class.java)

	@GetMapping("/hello/{name}")
	fun hello(@PathVariable name: String): ResponseEntity<Map<String, String>> {
		val response = mutableMapOf<String, String>()
		val greeting = properties.greeting
				.replace("\$name", name)
				.replace("\$hostname", hostname)
				.replace("\$version", properties.version)
		response["greeting"] = greeting
		response["version"] = properties.version
		response["hostname"] = hostname
		logger.info("Version ${properties.version} processed message for $name")
		return if (misbehave) doMisbehavior() else ResponseEntity.ok(response)
	}

	private fun doMisbehavior(): ResponseEntity<Map<String, String>> {
		logger.info("misbehaving!")
		return ResponseEntity
			.status(HttpStatus.SERVICE_UNAVAILABLE)
			.body(mapOf("misbehavior" to hostname))
	}

	@RequestMapping("/misbehave")
	fun flagMisbehave(): ResponseEntity<String> {
		misbehave = true
		logger.info("'misbehave' has been set to 'true'")
		return ResponseEntity
			.ok("Next request to /hello/{name} will return a 503\n")
	}

	@RequestMapping("/behave")
	fun flagBehave(): ResponseEntity<String> {
		misbehave = false
		logger.info("'misbehave' has been set to 'false'")
		return ResponseEntity.ok("Next request to /hello/{name} will return 200\n")
	}
}