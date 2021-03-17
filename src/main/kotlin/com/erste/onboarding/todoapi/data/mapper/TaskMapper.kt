package com.erste.onboarding.todoapi.data.mapper

import com.erste.onboarding.todoapi.data.entity.Task
import com.erste.onboarding.todoapi.data.entity.TaskBuilder
import com.erste.onboarding.todoapi.data.input.CreateTaskInput
import com.erste.onboarding.todoapi.data.input.UpdateTaskInput
import java.time.Instant
import java.util.*

class TaskMapper {
    companion object {
        fun toTask(createTaskInput: CreateTaskInput): Task {
            return TaskBuilder.builder()
                .setId(UUID.randomUUID())
                .setName(createTaskInput.name)
                .setNotes(createTaskInput.notes)
                .setIsCompleted(false)
                .setCreatedAt(Instant.now())
                .create()
        }

        fun toTask(updateTaskInput: UpdateTaskInput, task: Task): Task {
            return TaskBuilder.builder()
                .setId(task.id)
                .setName(updateTaskInput.name)
                .setNotes(updateTaskInput.notes ?: task.notes)
                .setIsCompleted(updateTaskInput.isComplete ?: task.isCompleted)
                .setCreatedAt(task.createdAt)
                .setCompletedAt(task.completedAt)
                .create()
        }
    }
}