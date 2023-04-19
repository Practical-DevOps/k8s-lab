plugins {
	kotlin("kapt")
}

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2021.0.2"

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}
dependencies {
	kapt("org.springframework.boot:spring-boot-configuration-processor")
}

jib {
	to {
		image = "${System.getenv("ACCOUNT_ID")}.dkr.ecr.us-east-1.amazonaws.com/${System.getenv("REPO_PREFIX")}-message"
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
				"org.opencontainers.image.title" to "message",
				"org.opencontainers.image.description" to "An example Message service",
				"org.opencontainers.image.version" to "$version",
				"org.opencontainers.image.authors" to "Dmytro Minochkin <dmytro.minochkin@gmail.com>",
				"org.opencontainers.image.url" to "https://gitlab.com/DmyMi/aws-k8s-lab"
			)
		)
	}
}
