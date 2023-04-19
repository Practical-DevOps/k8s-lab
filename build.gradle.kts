plugins {
    id("org.springframework.boot") version "2.6.7" apply false
    kotlin("jvm") version "1.6.21" apply false
    kotlin("plugin.spring") version "1.6.21" apply false
    id("com.google.cloud.tools.jib") version "3.2.1" apply false
}
buildscript {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        jcenter()
    }
    dependencies {
        classpath("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
    }
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}

configure(subprojects) {

    apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "com.google.cloud.tools.jib")

    dependencies {
        "implementation"("org.jetbrains.kotlin:kotlin-reflect")
        "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        "implementation"("org.springframework.boot:spring-boot-starter-actuator")
        "implementation"("io.micrometer:micrometer-registry-prometheus")
        "implementation"("org.springframework.boot:spring-boot-starter-web")
        "implementation"("org.springframework.cloud:spring-cloud-starter-sleuth")
        "implementation"("org.springframework.cloud:spring-cloud-sleuth-zipkin")
        "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin")
    }

    group = "academy.softserve"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
