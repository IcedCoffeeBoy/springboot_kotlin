package com.spring.fileupload

import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles

@Order(1)
@SpringBootTest
@ActiveProfiles("test")
class MainApplicationTests {

	@Test
	fun contextLoads() {
	}

}
