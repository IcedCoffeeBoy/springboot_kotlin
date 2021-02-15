package com.spring.fileupload.product


import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap


@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ProductControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @Order(1)
    fun getListOfProductShouldReturnSuccess() {
        mockMvc.perform(get("/api/v1/product")).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Order(2)
    fun getListOfProductWithPaginationShouldReturnSuccess() {
        val map: MultiValueMap<String, String> = LinkedMultiValueMap()
        val pageSize = (1..50).random()
        val pageNumber = (1..50).random()
        map["pageNumber"] = pageNumber.toString()
        map["pageSize"] = pageSize.toString()
        mockMvc
            .perform(get("/api/v1/product").queryParams(map))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect { result ->
                val body = objectMapper.readValue(result.response.contentAsString, Map::class.java)
                val outputPageNumber = (body["pageable"] as Map<*, *>)["pageNumber"]
                val outputPageSize = (body["pageable"] as Map<*, *>)["pageSize"]
                val products = objectMapper.convertValue(body["content"], object : TypeReference<List<Product?>?>() {})
                assert(outputPageNumber == pageNumber)
                assert(outputPageSize == pageSize)
                assert(products?.size!! <= pageSize)
            }
    }

    @Test
    @Order(3)
    fun getListOfProductByPriceRangeShouldReturnSuccess() {
        val map: MultiValueMap<String, String> = LinkedMultiValueMap()
        val fromPrice = (0..50).random()
        val toPrice = (0..50).random()
        map["pageNumber"] = "1"
        map["pageSize"] = "20"
        map["fromPrice"] = fromPrice.toString()
        map["toPrice"] = toPrice.toString()
        mockMvc
            .perform(get("/api/v1/product").queryParams(map))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect { result ->
                val body = objectMapper.readValue(result.response.contentAsString, Map::class.java)
                val products = objectMapper.convertValue(body["content"], object : TypeReference<List<Product?>?>() {})
                products?.forEach { product ->
                    assert(product?.unitPrice!! >= fromPrice)
                    assert(product.unitPrice!! <= toPrice)
                }
            }
    }

    @Test
    @Order(4)
    fun getListOfProductByQuantityRangeShouldReturnSuccess() {
        val map: MultiValueMap<String, String> = LinkedMultiValueMap()
        val minQuantity = (0..50).random()
        val maxQuantity = (0..50).random()
        map["pageNumber"] = "1"
        map["pageSize"] = "20"
        map["minQuantity"] = minQuantity.toString()
        map["maxQuantity"] = maxQuantity.toString()
        mockMvc
            .perform(get("/api/v1/product").queryParams(map))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect { result ->
                val body = objectMapper.readValue(result.response.contentAsString, Map::class.java)
                val products = objectMapper.convertValue(body["content"], object : TypeReference<List<Product?>?>() {})
                products?.forEach { product ->
                    assert(product?.quantity!! >= minQuantity)
                    assert(product.quantity!! <= maxQuantity)
                }
            }
    }
}

