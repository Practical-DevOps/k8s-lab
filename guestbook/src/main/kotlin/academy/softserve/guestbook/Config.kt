package academy.softserve.guestbook

import brave.sampler.Sampler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {
    @Bean
    fun defaultSampler(): Sampler = Sampler.ALWAYS_SAMPLE
}