package com.spring.fileupload.filecontent.service

import com.spring.fileupload.filecontent.database.FileRecord
import com.spring.fileupload.filecontent.service.converters.CSVToProductListConverter
import com.spring.fileupload.product.Product
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * Manages the content of the file
 */
@Service
class FileContentService {
    private val csvToProductListConverter = CSVToProductListConverter()

    fun convertToProductList(file: MultipartFile, record: FileRecord): List<Product?> {
        val products = csvToProductListConverter.convert(file)
        products.forEach { product ->
            product.fileRecordId = record.id
        }
        return products
    }
}