package com.example.kotlinquarkus.infra

import com.example.kotlinquarkus.domain.model.Person
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PanacheRepository : PanacheRepository<Person>