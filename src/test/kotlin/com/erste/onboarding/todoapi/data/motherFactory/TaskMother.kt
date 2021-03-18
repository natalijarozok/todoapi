package com.erste.onboarding.todoapi.data.motherFactory

import com.erste.onboarding.todoapi.data.builder.TaskBuilder
import com.erste.onboarding.todoapi.data.entity.Task
import java.time.Instant
import java.util.*

class TaskMother {
    companion object {
        fun createDefault(
            id: UUID = UUID.randomUUID(),
            name: String = "Run tests",
            notes: String? = "1. Run tests after build\n2. Fix failing tests",
            isComplete: Boolean? = false,
            createdAt: Instant = Instant.now(),
            completedAt: Instant? = null
        ): Task {
            return TaskBuilder.builder()
                .setId(id)
                .setName(name)
                .setNotes(notes)
                .setIsComplete(isComplete ?: false)
                .setCreatedAt(createdAt)
                .setCompletedAt(completedAt)
                .create()
        }
    }
}