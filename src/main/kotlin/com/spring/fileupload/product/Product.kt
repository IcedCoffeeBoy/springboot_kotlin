package com.spring.fileupload.product

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Product(
    @get:Id
    @get:GeneratedValue(generator = "uuid2")
    @get:GenericGenerator(name = "uuid2", strategy = "uuid2")
    @get:Type(type = "uuid-char")
    var id: UUID? = null,

    @get:Column
    var invoiceNo: String?,

    @get:Column
    var stockCode: String?,

    @get:Column
    var description: String?,

    @get:Column
    var quantity: Long?,

    @get:Column
    var invoiceDate: LocalDateTime?,

    @get:Column
    var unitPrice: Double?,

    @get:Column
    var customerID: String?,

    @get:Column
    var country: String?,

    @get:Column
    var fileRecordId: Long? = null,
)

fun Product.toDto(): ProductDto {
    return ProductDto(
        invoiceNo, stockCode, description,
        quantity, invoiceDate, unitPrice, customerID, country
    )
}

fun toDto(products: List<Product?>): List<ProductDto?> {
    return products.map { product -> product?.toDto() }
}
