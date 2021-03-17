package com.erste.onboarding.todoapi.data.db

import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors
import javax.persistence.EntityManager
import javax.persistence.Table
import javax.persistence.metamodel.EntityType
import javax.transaction.Transactional


@Component
class DatabaseCleaner(
    private val entityManager: EntityManager
) : InitializingBean {
    private var tableNames: List<String?>? = null
    private val schemaName = "public"

    /**
     * Uses the JPA metamodel to find all managed types then try to get the [Table] annotation's from each (if present) to discover the table name.
     * If the [Table] annotation is not defined then we skip that entity (oops :p)
     */
    override fun afterPropertiesSet() {
        val metamodel = entityManager.metamodel
        tableNames = metamodel.entities.stream()
            .map<String?> { entity: EntityType<*> ->
                val annotation = entity.javaType.getAnnotation(
                    Table::class.java
                )
                StringBuilder(schemaName).append(".").append(annotation?.name).toString()
            }
            .filter { obj: String? -> Objects.nonNull(obj) }
            .collect(Collectors.toList())
    }

    /**
     * Utility method that truncates all identified tables
     */
    @Transactional
    fun truncate() {
        entityManager.flush()
        tableNames!!.forEach(
            Consumer { tableName: String? ->
                entityManager.createNativeQuery("TRUNCATE TABLE $tableName CASCADE").executeUpdate()
            }
        )
    }
}