package com.erste.onboarding.todoapi.controller

import com.erste.onboarding.todoapi.data.entity.Task
import com.erste.onboarding.todoapi.data.input.CreateTaskInput
import com.erste.onboarding.todoapi.data.input.UpdateTaskInput
import com.erste.onboarding.todoapi.data.mapper.TaskMapper
import com.erste.onboarding.todoapi.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("$API_V1_BASE/tasks")
class TaskController(
    private val taskService: TaskService
) {

    @GetMapping(value = ["/{taskId}"])
    fun getTask(@PathVariable("taskId") taskId: UUID): ResponseEntity<Task> {
        val task = taskService.getTaskById(taskId)
        return ResponseEntity.status(HttpStatus.OK).body(task)
    }

    @GetMapping
    fun getTasks(): ResponseEntity<List<Task>> {
        val tasks = taskService.getAllTasks()
        return ResponseEntity.ok(tasks)
    }

    @PostMapping
    fun create(@RequestBody createTaskInput: CreateTaskInput): ResponseEntity<Task> {
        val task = TaskMapper.toTask(createTaskInput).let { taskService.saveTask(it) }
        return ResponseEntity.ok(task)
    }

    @DeleteMapping(value = ["/{taskId}"])
    fun delete(@PathVariable("taskId") taskId: UUID): ResponseEntity<Boolean> {
        val result = taskService.deleteTask(taskId)
        return ResponseEntity.ok(result)
    }

    @PatchMapping(value = ["/{taskId}"])
    fun update(
        @PathVariable("taskId") taskId: UUID,
        @RequestBody updateTaskInput: UpdateTaskInput
    ): ResponseEntity<Task> {
        val task = taskService.updateTask(taskId, updateTaskInput)
        return ResponseEntity.ok(task)
    }
}