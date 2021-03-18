package com.erste.onboarding.todoapi.data.input

data class UpdateTaskInput(
    val name: String? = null,
    val notes: String? = null,
    val isComplete: Boolean
)