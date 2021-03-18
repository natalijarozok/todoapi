package com.erste.onboarding.todoapi.exception.error

data class ResponseEntityError(
    val code: String,
    val message: String
)