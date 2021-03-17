package com.erste.onboarding.todoapi.data.motherFactory

import com.erste.onboarding.todoapi.data.input.UpdateTaskInput

class UpdateTaskInputMother {
    companion object {
        fun createDefault(
            name: String = "Run tests all",
            notes: String? = "1. Run tests after build all\n2. Fix failing tests all",
            isComplete: Boolean? = true
        ): UpdateTaskInput {
            return UpdateTaskInput(
                name = name,
                notes = notes,
                isComplete = isComplete
            )
        }
    }
}