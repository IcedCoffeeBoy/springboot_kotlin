package com.spring.fileupload.product

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import javax.transaction.Transactional

@Service
@Transactional
class ProductService(
    private val productRepository: ProductRepository
) {
    private val specificationsBuilder = ProductSpecificationsBuilder()
    private val logger = LoggerFactory.getLogger(javaClass)

    fun find(request: ProductFindRequest): Page<Product?> {
        return productRepository.findAll(
            specificationsBuilder.toSpecification(request),
            specificationsBuilder.toPageable(request)
        )
    }

    @Async
    fun saveAll(products: List<Product?>): CompletableFuture<Boolean?> {
        return try {
            val start = System.currentTimeMillis()
            productRepository.saveAll(products)
            logger.info("Elapsed time: {}", System.currentTimeMillis() - start)
            CompletableFuture.completedFuture(true)
        } catch (e: Exception) {
            logger.error(e.toString())
            CompletableFuture.completedFuture(false)
        }
    }
}