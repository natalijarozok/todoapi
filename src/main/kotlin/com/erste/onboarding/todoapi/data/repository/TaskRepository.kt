package com.erste.onboarding.todoapi.data.repository

import com.erste.onboarding.todoapi.data.entity.Task
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TaskRepository : CrudRepository<Task, UUID> {
    fun findByName(name: String): Task?
    fun getAllBy(): List<Task>
    fun save(task: Task): Task
}