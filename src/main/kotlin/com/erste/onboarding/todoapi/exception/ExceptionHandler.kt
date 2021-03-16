package com.erste.onboarding.todoapi.exception

import com.erste.onboarding.todoapi.exception.error.ResponseEntityError
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(CustomException::class)])
    fun handle(ex: CustomException): ResponseEntity<ResponseEntityError> {
        val error = ResponseEntityError(
            code = ex.errorCode.name,
            message = ex.errorMessage
        )
        return ResponseEntity(error, ex.statusCode)
    }
}