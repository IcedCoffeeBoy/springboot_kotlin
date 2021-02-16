package com.spring.fileupload.config.exception

import com.spring.fileupload.common.exception.BusinessException
import com.spring.fileupload.common.exception.NotFoundException
import com.spring.fileupload.common.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.validation.ValidationException


@ControllerAdvice
class RestResponseEntityExceptionHandler() : ResponseEntityExceptionHandler() {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleMaxSizeException(exc: MaxUploadSizeExceededException?): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
            .body(ErrorResponse(error = "FILE_ERROR", reason = "File too large, exceeded max size"))
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exc: NotFoundException, request: HttpServletRequest?): ResponseEntity<ErrorResponse> {
        logger.error(exc)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorResponse(error = "NOT_FOUND", reason = exc.message, path = request?.requestURI)
        )
    }

    @ExceptionHandler(value = [BusinessException::class])
    protected fun handleBusinessException(
        exception: BusinessException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        logger.error(exception.toString())
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
            ErrorResponse(error = "CUSTOMISED_ERROR", reason = exception.message, path = request.requestURI)
        )
    }

    @ExceptionHandler(value = [ValidationException::class])
    protected fun handleValidationException(
        exception: BusinessException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        logger.error(exception.toString())
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
            ErrorResponse(error = "VALIDATION_FAILED", reason = exception.message, path = request.requestURI)
        )
    }

    override fun handleBindException(
        ex: BindException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        logger.error(ex.toString())
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
            ErrorResponse(error = "VALIDATION_FAILED", reason = ex.fieldError?.defaultMessage)
        )
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        logger.error(
            String.format("Api method argument not valid for %s", ex.message)
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(error = "INPUTS_ERROR", reason = ex.message)
        )
    }

    override fun handleHttpMessageNotReadable(
        exception: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val response: MutableMap<String, Any> = LinkedHashMap()
        response["status"] = status.value()
        response["errors"] = "Request not valid"
        return ResponseEntity(response, headers, status)
    }
}