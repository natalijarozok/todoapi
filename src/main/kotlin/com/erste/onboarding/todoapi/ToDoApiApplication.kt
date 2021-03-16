package com.erste.onboarding.todoapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ToDoApiApplication

fun main(args: Array<String>)
{
	runApplication<ToDoApiApplication>(*args)
}
