package com.spring.fileupload.config.web

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    val ALLOWED_ORIGINS = arrayOf("*")
    val ALLOWED_METHODS = arrayOf("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedOrigins(*ALLOWED_ORIGINS)
                .allowedMethods(*ALLOWED_METHODS)
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/").setViewName("forward:/index.html")
        registry.addViewController("/upload").setViewName("forward:/upload.html")
        registry.addViewController("/swagger").setViewName("redirect:/swagger-ui.html")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {}

}