package com.spring.fileupload.product

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name ="Product Api")
@RestController
@RequestMapping(value = ["/api/v1/product"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(
    private val productService: ProductService
) {
    @Operation(summary = "List/Search of product")
    @GetMapping
    fun find(request: ProductFindRequest): ResponseEntity<Page<Product?>> {
        return ResponseEntity.ok(productService.find(request))
    }
}