package com.erste.onboarding.todoapi.data.builder

import com.erste.onboarding.todoapi.data.entity.Task
import java.time.Instant
import java.util.*


class TaskBuilder {
    private lateinit var id: UUID
    private lateinit var name: String
    private var notes: String? = null
    private var isComplete = false
    private var createdAt: Instant = Instant.now()
    private var completedAt: Instant? = null
    fun setId(id: UUID): TaskBuilder {
        this.id = id
        return this
    }

    fun setName(name: String): TaskBuilder {
        this.name = name
        return this
    }

    fun setNotes(notes: String?): TaskBuilder {
        this.notes = notes
        return this
    }

    fun setIsComplete(isComplete: Boolean): TaskBuilder {
        this.isComplete = isComplete
        return this
    }

    fun setCreatedAt(createdAt: Instant): TaskBuilder {
        this.createdAt = createdAt
        return this
    }

    fun setCompletedAt(completedAt: Instant?): TaskBuilder {
        this.completedAt = completedAt
        return this
    }

    fun create(): Task {
        return Task(id, name, notes, isComplete, createdAt, completedAt)
    }

    companion object {
        fun builder(): TaskBuilder {
            return TaskBuilder()
        }
    }
}