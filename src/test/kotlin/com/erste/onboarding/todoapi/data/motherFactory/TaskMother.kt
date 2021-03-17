package com.erste.onboarding.todoapi.data.motherFactory

import com.erste.onboarding.todoapi.data.entity.Task
import com.erste.onboarding.todoapi.data.entity.TaskBuilder
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
                .setIsCompleted(isComplete ?: false)
                .setCreatedAt(createdAt)
                .setCompletedAt(completedAt)
                .create()
        }
    }
}