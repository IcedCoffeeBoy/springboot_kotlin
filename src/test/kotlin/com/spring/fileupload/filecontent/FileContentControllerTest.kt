package com.spring.fileupload.filecontent

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.io.File
import java.io.FileInputStream

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation::class)
class FileContentControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    @Order(1)
    fun postFileShouldReturnCreated() {
        val f = File(TestFileData.DATA_PATH_1)
        val fi = FileInputStream(f)
        val file = MockMultipartFile("file", f.name, "multipart/form-data", fi)
        mockMvc.perform(
            MockMvcRequestBuilders
                .multipart("/api/v1/file")
                .file(file)
        ).andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    @Order(2)
    fun postDuplicateFileShouldReturnFailed() {
        val f = File(TestFileData.DATA_PATH_1)
        val fi = FileInputStream(f)
        val file = MockMultipartFile("file", f.name, "multipart/form-data", fi)
        mockMvc.perform(
            MockMvcRequestBuilders
                .multipart("/api/v1/file")
                .file(file)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    @Order(3)
    fun postEmptyFileShouldReturnCreated() {
        val f = File(TestFileData.DATA_PATH_2)
        val fi = FileInputStream(f)
        val file = MockMultipartFile("file", f.name, "multipart/form-data", fi)
        mockMvc.perform(
            MockMvcRequestBuilders
                .multipart("/api/v1/file")
                .file(file)
        ).andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    @Order(4)
    fun postLargeFileShouldReturnCreated() {
        val f = File(TestFileData.DATA_PATH_3)
        val fi = FileInputStream(f)
        val file = MockMultipartFile("file", f.name, "multipart/form-data", fi)
        mockMvc.perform(
            MockMvcRequestBuilders
                .multipart("/api/v1/file")
                .file(file)
        ).andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    @Order(5)
    fun postErroneousFileShouldReturn4xx() {
        val f = File(TestFileData.DATA_PATH_4)
        val fi = FileInputStream(f)
        val file = MockMultipartFile("file", f.name, "multipart/form-data", fi)
        mockMvc.perform(
            MockMvcRequestBuilders
                .multipart("/api/v1/file")
                .file(file)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }
}
