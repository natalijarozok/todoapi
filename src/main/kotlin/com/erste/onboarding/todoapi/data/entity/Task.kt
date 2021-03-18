package com.erste.onboarding.todoapi.data.entity

import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "task", schema = "public")
data class Task(
    @Id
    @Column(nullable = false)
    val id: UUID,

    @Column(columnDefinition = "varchar(255) not null")
    val name: String,

    @Column(columnDefinition = "varchar(500)")
    val notes: String?,

    @Column(columnDefinition = "boolean default false not null")
    val isComplete: Boolean,

    @Column(columnDefinition = "timestamp with time zone default CURRENT_DATE not null")
    val createdAt: Instant,

    @Column(columnDefinition = "timestamp with time zone")
    val completedAt: Instant?

) : Comparable<Task> {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that: Task = other as Task
        return id == that.id &&
                name == that.name &&
                notes == that.notes &&
                isComplete == that.isComplete &&
                createdAt == that.createdAt &&
                completedAt == that.completedAt
    }

    override fun compareTo(other: Task): Int = when {
        id < other.id -> -1
        id > other.id -> 1
        else          -> 0
    }
}
