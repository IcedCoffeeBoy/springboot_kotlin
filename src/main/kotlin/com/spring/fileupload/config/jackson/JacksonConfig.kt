package com.spring.fileupload.config.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Configuration
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean

import org.springframework.context.annotation.Primary


@Configuration
class JacksonConfig {
//    @Bean
//    @Primary
//    fun objectMapper(): ObjectMapper {
//        val module = JavaTimeModule()
//        return ObjectMapper()
//            .registerModule(KotlinModule())
//            .registerModule(module)
//    }
}