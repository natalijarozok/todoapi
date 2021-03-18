package com.erste.onboarding.todoapi.data.motherFactory

import com.erste.onboarding.todoapi.data.input.UpdateTaskInput

class UpdateTaskInputMother {
    companion object {
        fun createDefault(
            name: String? = "Run tests all",
            notes: String? = "1. Run tests after build all\n2. Fix failing tests all",
            isComplete: Boolean = true
        ): UpdateTaskInput {
            return UpdateTaskInput(
                name = name,
                notes = notes,
                isComplete = isComplete
            )
        }

        fun createWithIsCompleteOnly(): UpdateTaskInput {
            return UpdateTaskInput(
                isComplete = true
            )
        }

        fun createWithNameAndIsComplete(
            name: String? = "Run tests all"
        ): UpdateTaskInput {
            return UpdateTaskInput(
                name = name,
                isComplete = true
            )
        }

        fun createWithNotesAndIsComplete(
            notes: String? = "1. Run tests after build all\n2. Fix failing tests all"
        ): UpdateTaskInput {
            return UpdateTaskInput(
                notes = notes,
                isComplete = true
            )
        }
    }
}