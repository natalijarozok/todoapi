package com.erste.onboarding.todoapi.exception

import com.erste.onboarding.todoapi.exception.error.ErrorIndex
import org.springframework.http.HttpStatus
import java.util.*

class TaskNotFoundException private constructor(
    message: String
) : CustomException(
    ErrorIndex.TASK_NOT_FOUND,
    message,
    HttpStatus.NOT_FOUND
) {
    companion object {
        fun id(taskId: UUID) = TaskNotFoundException("Task with id: $taskId was not found")
    }
}