package com.spring.fileupload.product

import java.time.LocalDateTime

data class ProductDto(
    var invoiceNo: String?,
    var stockCode: String?,
    var description: String?,
    var quantity: Long?,
    var invoiceDate: LocalDateTime?,
    var unitPrice: Double?,
    var customerID: String?,
    var country: String?
)
