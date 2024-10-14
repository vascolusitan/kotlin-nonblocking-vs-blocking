package com.example.kotlinspringwebflux.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "person")
data class Person(
    @Id
    val id: Long,
    val name: String,
    val age: Int
)