package com.erste.onboarding.todoapi.facade

import com.erste.onboarding.todoapi.data.entity.Task
import com.erste.onboarding.todoapi.data.input.UpdateTaskInput
import com.erste.onboarding.todoapi.data.mapper.TaskMapper
import com.erste.onboarding.todoapi.data.repository.TaskRepository
import com.erste.onboarding.todoapi.exception.TaskNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class TaskFacade(
    private val taskRepository: TaskRepository
) {

    fun deleteTask(taskId: UUID): Boolean {
        getTaskById(taskId).let { taskRepository.delete(it) }
        return true
    }

    fun getAllTasks(): List<Task> {
        return taskRepository.getAllBy()
    }

    fun getTaskById(taskId: UUID): Task {
        return findByTaskId(taskId) ?: throw TaskNotFoundException.id(taskId)
    }

    fun saveTask(task: Task): Task {
        return taskRepository.save(task)
    }

    fun updateTask(taskId: UUID, updateTaskInput: UpdateTaskInput): Task {
        return getTaskById(taskId).let { task ->
            TaskMapper.toTask(updateTaskInput, task).let { taskRepository.save(it) }
        }
    }

    private fun findByTaskId(taskId: UUID): Task? {
        return taskRepository.getById(taskId)
    }
}
