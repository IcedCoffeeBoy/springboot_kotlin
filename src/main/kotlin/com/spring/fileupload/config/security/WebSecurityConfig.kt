package com.spring.fileupload.config.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig() : WebSecurityConfigurerAdapter() {
    val logger: Logger = LoggerFactory.getLogger(javaClass)


    override fun configure(http: HttpSecurity) {
        logger.info("Allowed list of Api: ${ALLOWED_API.contentToString()}")
        logger.info("Allowed list of resources: ${ALLOWED_RESOURCES.contentToString()}")
        http
            .cors().and()
            .csrf().disable()
        http
            .authorizeRequests()
            .antMatchers(*ALLOWED_API).permitAll()
            .antMatchers(*ALLOWED_RESOURCES).permitAll()
            .anyRequest().authenticated()
    }

    companion object {
        val ALLOWED_API = arrayOf("/**")
        val ALLOWED_RESOURCES = arrayOf(
            "/",
            "/*",
            "/favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/**/*.json",
            "/**/*.ttf"
        )
    }
}