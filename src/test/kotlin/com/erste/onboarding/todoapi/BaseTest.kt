package com.erste.onboarding.todoapi

import com.erste.onboarding.todoapi.data.db.DatabaseCleaner
import com.erste.onboarding.todoapi.data.db.DbDataManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BaseTest{

    @Autowired
    lateinit var dbCleaner: DatabaseCleaner

    @Autowired
    lateinit var dbDataManager: DbDataManager


    @BeforeEach
    fun cleanDatabase() {
        dbCleaner.truncate()
    }

}