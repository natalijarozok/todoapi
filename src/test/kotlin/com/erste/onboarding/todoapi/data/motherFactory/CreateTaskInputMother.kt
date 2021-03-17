package com.erste.onboarding.todoapi.data.motherFactory

import com.erste.onboarding.todoapi.data.input.CreateTaskInput

class CreateTaskInputMother {
    companion object {
        fun createDefault(
            name: String = "Run tests all",
            notes: String = "1. Run tests after build all\n2. Fix failing tests all"
        ): CreateTaskInput {
            return CreateTaskInput(
                name = name,
                notes = notes
            )
        }
    }
}