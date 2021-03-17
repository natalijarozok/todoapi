package com.erste.onboarding.todoapi.data.input

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class UpdateTaskInput(
    val name: String,
    val notes: String?,
    val isComplete: Boolean?
)