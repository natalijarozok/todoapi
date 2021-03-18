package com.erste.onboarding.todoapi.data.db

import com.erste.onboarding.todoapi.data.entity.Task
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Component
class DbDataManager(
    private val entityManager: EntityManager
) {

    @Transactional
    fun feedTaskTable(task: Task) {
        entityManager.createNativeQuery(
            "INSERT INTO task (id, name, notes, is_complete, created_at)" +
                    " values(" +
                    "'${task.id}', " +
                    "'${task.name}', " +
                    "'${task.notes}', " +
                    "${task.isComplete}, " +
                    "'${task.createdAt}'" +
                    ")"
        ).executeUpdate()
    }

    fun selectFromTaskTable(taskId: UUID): Task? {
        val tasks = entityManager.createNativeQuery(
            "SELECT CAST(id as varchar) id, name, notes, is_complete, created_at, completed_at" +
                    " FROM task WHERE id = '${taskId}'", Task::class.java
        ).resultList

        return if (tasks.isEmpty()) null
        else
            tasks.first() as Task
    }
}