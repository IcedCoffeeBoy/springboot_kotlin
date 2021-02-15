package com.spring.fileupload.product

import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/v1/product"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(
    private val productService: ProductService
) {
    @GetMapping
    fun find(request: ProductFindRequest): ResponseEntity<Page<Product?>> {
        return ResponseEntity.ok(productService.find(request))
    }
}