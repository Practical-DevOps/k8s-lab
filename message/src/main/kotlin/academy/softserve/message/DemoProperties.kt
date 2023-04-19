package academy.softserve.message

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("demo")
data class DemoProperties(
        val greeting: String,
        val version: String = "1.0"
)