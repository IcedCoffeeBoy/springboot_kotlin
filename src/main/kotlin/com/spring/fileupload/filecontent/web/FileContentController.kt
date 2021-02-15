package com.spring.fileupload.filecontent.web

import com.spring.fileupload.filecontent.database.toDto
import com.spring.fileupload.filecontent.model.FileRecordDto
import com.spring.fileupload.filecontent.model.FileStatus
import com.spring.fileupload.filecontent.service.FileContentService
import com.spring.fileupload.filecontent.service.FileRecordService
import com.spring.fileupload.product.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Tag(name = "File Api")
@RestController
@RequestMapping(value = ["/api/v1/file"], produces = [MediaType.APPLICATION_JSON_VALUE])
class FileContentController(
    private val fileRecordService: FileRecordService,
    private val fileContentService: FileContentService,
    private val productService: ProductService
) {
    @Operation(summary = "Check for uploaded file status using dataId")
    @GetMapping("/{dataId}")
    fun findByDataId(@PathVariable("dataId") dataId: String): ResponseEntity<FileRecordDto> {
        return ResponseEntity.ok(fileRecordService.findByDataId(dataId).toDto())
    }

    @Operation(summary = "Upload a file containing products")
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun uploadProductFile(@RequestParam("file") file: MultipartFile): ResponseEntity<*> {
        val record = fileRecordService.validateAndSave(file)
        val recordId = record.id
        val products = fileContentService.convertToProductList(file, record)
        productService.saveAll(products).thenApply { outcome: Boolean ->
            if (outcome) {
                fileRecordService.update(recordId, FileStatus.COMPLETED)
            } else {
                fileRecordService.update(recordId, FileStatus.ERROR)
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(record.toDto())
    }
}
