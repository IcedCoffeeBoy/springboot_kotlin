package com.spring.fileupload.config.exception

import com.spring.fileupload.common.exception.BusinessException
import com.spring.fileupload.common.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleMaxSizeException(exc: MaxUploadSizeExceededException?): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File too large!")
    }

    @ExceptionHandler(value = [BusinessException::class])
    protected fun handleBusinessException(exception: BusinessException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        logger.error(exception.toString())
        val error = ErrorResponse(exception.message)
        error.reason = exception.message
        error.path = request.requestURI
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error)
    }

    override fun handleHttpMessageNotReadable(exception: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val response: MutableMap<String, Any> = LinkedHashMap()
        response["status"] = status.value()
        response["errors"] = "Request not valid"
        return ResponseEntity(response, headers, status)
    }
}