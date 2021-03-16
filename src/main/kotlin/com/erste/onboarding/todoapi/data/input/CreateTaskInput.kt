package com.erste.onboarding.todoapi.data.input

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class CreateTaskInput(
    val name: String,
    val notes: String?
)