package com.erste.onboarding.todoapi

import com.erste.onboarding.todoapi.data.entity.Task
import com.erste.onboarding.todoapi.data.input.UpdateTaskInput
import com.erste.onboarding.todoapi.data.motherFactory.CreateTaskInputMother
import com.erste.onboarding.todoapi.data.motherFactory.TaskMother
import com.erste.onboarding.todoapi.data.motherFactory.UpdateTaskInputMother
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.boot.web.server.LocalServerPort


class RestControllerIntegrationTests : BaseTest() {

    @LocalServerPort
    var serverPort = 0

    val apiEndpoint = "/api/v1/tasks"

    @BeforeAll
    fun init() {
        RestAssured.port = serverPort
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }

    @Test
    fun `get task by id`() {
        val taskExpected = TaskMother.createDefault()
        dbDataManager.feedTaskTable(taskExpected)

        val response = Given {
            contentType(ContentType.JSON)
            pathParam("taskId", taskExpected.id)
        } When {
            get("$apiEndpoint/{taskId}")
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
        } Extract {
            response()
        }
        val taskActual = response.`as`(Task::class.java)

        assertEquals(taskExpected, taskActual)
    }

    @Test
    fun `get all tasks`() {
        val tasksExpected = ArrayList<Task>()
        tasksExpected.add(TaskMother.createDefault())
        tasksExpected.add(TaskMother.createDefault())
        tasksExpected.forEach { dbDataManager.feedTaskTable(it) }

        val response = Given {
            contentType(ContentType.JSON)
        } When {
            get(apiEndpoint)
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
        } Extract {
            response()
        }

        val tasksActual = response.`as`(Array<Task>::class.java).toList()

        assertEquals(tasksExpected.sorted(), tasksActual.sorted())
    }

    @Test
    fun `create new task`() {
        val payload = CreateTaskInputMother.createDefault()
        val taskExpected = TaskMother.createDefault(name = payload.name, notes = payload.notes ?: "")

        val response = Given {
            contentType(ContentType.JSON)
            body(payload)
        } When {
            post(apiEndpoint)
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
        } Extract {
            response()
        }

        val taskActual = response.`as`(Task::class.java)

        assertEquals(taskExpected.name, taskActual.name)
        assertEquals(taskExpected.notes, taskActual.notes)
        assertEquals(taskExpected.isComplete, taskActual.isComplete)
    }

    @Test
    fun `delete task`() {
        val task = TaskMother.createDefault()
        dbDataManager.feedTaskTable(task)

        val response = Given {
            contentType(ContentType.JSON)
            pathParam("taskId", task.id)
        } When {
            delete("$apiEndpoint/{taskId}")
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
        } Extract {
            response()
        }

        val responseActual = response.`as`(Boolean::class.java)

        assertEquals(true, responseActual)
    }

    @Test
    fun `update task with full data`() {
        val updatePayload = UpdateTaskInputMother.createDefault()
        testUpdateTask(updatePayload)
    }

    @Test
    fun `update task with isComplete field`() {
        val updatePayload = UpdateTaskInputMother.createWithIsCompleteOnly()
        testUpdateTask(updatePayload)
    }

    @Test
    fun `update task with Name and isComplete fields`() {
        val updatePayload = UpdateTaskInputMother.createWithNameAndIsComplete()
        testUpdateTask(updatePayload)
    }

    @Test
    fun `update task with Notes and isComplete fields`() {
        val updatePayload = UpdateTaskInputMother.createWithNotesAndIsComplete()
        testUpdateTask(updatePayload)
    }

    private fun testUpdateTask(updatePayload: UpdateTaskInput) {
        val task = TaskMother.createDefault()
        dbDataManager.feedTaskTable(task)

        val taskExpected = TaskMother.createDefault(
            name = updatePayload.name ?: task.name,
            notes = updatePayload.notes ?: task.notes,
            isComplete = updatePayload.isComplete
        )

        val response = Given {
            contentType(ContentType.JSON)
            pathParam("taskId", task.id)
            body(updatePayload)
        } When {
            patch("$apiEndpoint/{taskId}")
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
        } Extract {
            response()
        }

        val taskActual = response.`as`(Task::class.java)

        assertEquals(taskExpected.name, taskActual.name)
        assertEquals(taskExpected.notes, taskActual.notes)
        assertEquals(taskExpected.isComplete, taskActual.isComplete)
    }
}
