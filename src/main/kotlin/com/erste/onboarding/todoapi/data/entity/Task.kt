package com.erste.onboarding.todoapi.data.entity

import com.github.pozo.KotlinBuilder
import java.time.Instant
import java.util.*
import javax.persistence.*

@KotlinBuilder
@Entity
@Table(name = "task", schema = "public")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    val id: UUID,

    @Column(columnDefinition = "varchar(255) not null")
    val name: String,

    @Column(columnDefinition = "varchar(500)")
    val notes: String?,

    @Column(columnDefinition = "boolean default false not null")
    val isCompleted: Boolean,

    @Column(columnDefinition = "timestamp with time zone default CURRENT_DATE not null")
    val createdAt: Instant,

    @Column(columnDefinition = "timestamp with time zone default CURRENT_DATE not null")
    val completedAt: Instant

)
