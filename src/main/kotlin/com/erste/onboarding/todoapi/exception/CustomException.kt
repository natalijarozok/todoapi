package com.erste.onboarding.todoapi.exception

import com.erste.onboarding.todoapi.exception.error.ErrorIndex
import org.springframework.http.HttpStatus


open class CustomException(
    val errorCode: ErrorIndex,
    val errorMessage: String,
    val statusCode: HttpStatus
) : RuntimeException()