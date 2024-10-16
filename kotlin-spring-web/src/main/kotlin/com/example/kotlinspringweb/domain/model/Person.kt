package com.example.kotlinspringweb.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,
    val name: String = "",
    val age: Int = 0
)