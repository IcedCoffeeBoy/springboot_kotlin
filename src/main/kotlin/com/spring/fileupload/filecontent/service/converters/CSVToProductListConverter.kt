package com.spring.fileupload.filecontent.service.converters

import com.spring.fileupload.common.exception.BusinessException
import com.spring.fileupload.common.model.Converter
import com.spring.fileupload.product.Product
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.slf4j.LoggerFactory
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.stream.Collectors


class CSVToProductListConverter : Converter<MultipartFile, List<Product>> {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val csvFormat = CSVFormat.newFormat(',').withQuote('"').withFirstRecordAsHeader()
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy H:mm")
    override fun convert(file: MultipartFile): List<Product> {
        try {
            val inputStream: InputStream = BufferedInputStream(file.inputStream)
            val csvParser = CSVParser.parse(inputStream, StandardCharsets.UTF_8, csvFormat)
            return csvParser.records.stream()
                .map { record: CSVRecord ->
                    convertRecordToProduct(record)
                }.collect(Collectors.toList())
        } catch (e: IOException) {
            logger.error("Unable to read CSV format")
            throw BusinessException("Error trying to read the CSV format. Please check your CSV format.")
        }
    }

    private fun convertRecordToProduct(record: CSVRecord): Product {
        return try {
            Product(
                id = null,
                invoiceNo = record["InvoiceNo"],
                stockCode = record["StockCode"],
                description = record["Description"],
                quantity = record["Quantity"].toLong(),
                invoiceDate = LocalDateTime.from(dateTimeFormatter.parse(record["InvoiceDate"])),
                unitPrice = record["UnitPrice"].toDouble(),
                customerID = record["CustomerID"],
                country = record["Country"],
                fileRecordId = null
            )
        } catch (e: DateTimeParseException) {
            logger.error(e.toString())
            throw BusinessException("There is an incorrect date time format in line " + record.recordNumber)
        } catch (e: Exception) {
            logger.error(e.toString())
            throw BusinessException("There is an error in line " + record.recordNumber + record.toString())
        }
    }
}