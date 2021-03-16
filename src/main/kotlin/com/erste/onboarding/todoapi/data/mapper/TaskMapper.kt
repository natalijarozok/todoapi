package com.erste.onboarding.todoapi.data.mapper

import com.erste.onboarding.todoapi.data.entity.Task
import com.erste.onboarding.todoapi.data.entity.TaskBuilder
import com.erste.onboarding.todoapi.data.input.CreateTaskInput
import java.time.Instant
import java.util.*

class TaskMapper {
    companion object {
        fun toTask(createTaskInput: CreateTaskInput): Task {
            val task = TaskBuilder.builder()
                .setId(UUID.randomUUID())
                .setName(createTaskInput.name)
                .setNotes(createTaskInput.notes)
                .setIsCompleted(false)
                .setCreatedAt(Instant.now())
                .create()
            return task
        }
    }
}