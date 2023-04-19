plugins {
	kotlin("plugin.jpa") version "1.6.21"
}

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2021.0.2"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("mysql:mysql-connector-java")
	implementation("io.zipkin.brave:brave-instrumentation-mysql8")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

jib {
	to {
		image = "${System.getenv("ACCOUNT_ID")}.dkr.ecr.us-east-1.amazonaws.com/${System.getenv("REPO_PREFIX")}-guestbook"
		credHelper {
			helper = "ecr-login"
		}
		tags = setOf("latest")
	}
	container {
		ports = listOf("8080")
		labels.putAll(
			mapOf(
				"maintainer" to "Dmytro Minochkin <dmytro.minochkin@gmail.com>",
				"org.opencontainers.image.title" to "guestbook",
				"org.opencontainers.image.description" to "An example Guestbook service",
				"org.opencontainers.image.version" to "$version",
				"org.opencontainers.image.authors" to "Dmytro Minochkin <dmytro.minochkin@gmail.com>",
				"org.opencontainers.image.url" to "https://gitlab.com/DmyMi/aws-k8s-lab"
			)
		)
	}
}