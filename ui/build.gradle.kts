plugins {
	kotlin("kapt")
}

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2021.0.2"

dependencies {
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.springframework.session:spring-session-data-redis")
	implementation("redis.clients:jedis")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

jib {
	to {
		image = "${System.getenv("ACCOUNT_ID")}.dkr.ecr.us-east-1.amazonaws.com/${System.getenv("REPO_PREFIX")}-ui"
		credHelper {
			helper = "ecr-login"
		}
		tags = setOf("v1", "latest")
	}
	container {
		ports = listOf("8080")
		labels.putAll(
			mapOf(
				"maintainer" to "Dmytro Minochkin <dmytro.minochkin@gmail.com>",
				"org.opencontainers.image.title" to "ui",
				"org.opencontainers.image.description" to "An example UI service",
				"org.opencontainers.image.version" to "$version",
				"org.opencontainers.image.authors" to "Dmytro Minochkin <dmytro.minochkin@gmail.com>",
				"org.opencontainers.image.url" to "https://gitlab.com/DmyMi/aws-k8s-lab"
			)
		)
	}
}
