package com.example.kotlinspringwebflux.infra

import com.example.kotlinspringwebflux.domain.model.Person
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: ReactiveCrudRepository<Person, Long>