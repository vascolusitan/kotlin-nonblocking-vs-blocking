package com.example.kotlinquarkus.domain.model

import io.quarkus.hibernate.reactive.panache.PanacheEntity
import jakarta.persistence.Entity

@Entity
data class Person (
    public var name: String,
    public var age: Int
) : PanacheEntity() {
    constructor() : this("", 0)
}
