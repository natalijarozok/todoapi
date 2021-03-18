package com.erste.onboarding.todoapi

import com.erste.onboarding.todoapi.data.entity.Task
import com.erste.onboarding.todoapi.data.motherFactory.TaskMother
import com.erste.onboarding.todoapi.data.repository.TaskRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class RepositoryIntegrationTests : BaseTest() {

    @Autowired
    lateinit var sut: TaskRepository

    @Test
    fun `get task by id`() {
        val taskExpected = TaskMother.createDefault()
        dbDataManager.feedTaskTable(taskExpected)

        val taskActual = sut.getById(taskExpected.id)

        assertEquals(taskExpected, taskActual)
    }

    @Test
    fun `get all tasks`() {
        val tasksExpected = ArrayList<Task>()
        tasksExpected.add(TaskMother.createDefault())
        tasksExpected.add(TaskMother.createDefault())
        tasksExpected.forEach { dbDataManager.feedTaskTable(it) }

        val tasksActual = sut.getAllBy()

        assertEquals(tasksExpected.sorted(), tasksActual.sorted())
    }

    @Test
    fun `create new task`() {
        val taskExpected = TaskMother.createDefault()

        sut.save(taskExpected)

        val taskActual = dbDataManager.selectFromTaskTable(taskExpected.id)

        assertEquals(taskExpected, taskActual)
    }

    @Test
    fun `delete task`() {
        val task = TaskMother.createDefault()
        dbDataManager.feedTaskTable(task)

        sut.deleteById(task.id)


        val taskActual = dbDataManager.selectFromTaskTable(task.id)

        assertEquals(null, taskActual)
    }

    @Test
    fun `update task`() {
        val task = TaskMother.createDefault()
        dbDataManager.feedTaskTable(task)

        val taskExpected = TaskMother.createDefault(
            id = task.id,
            name = "Updated name",
            notes = "Updated notes",
            isComplete = true
        )

        sut.save(taskExpected)

        val taskActual = dbDataManager.selectFromTaskTable(taskExpected.id)

        assertEquals(taskExpected, taskActual)
    }
}
