package com.example.kotlinquarkus.domain.service

import com.example.kotlinquarkus.domain.model.Person
import com.example.kotlinquarkus.domain.service.exception.NotFoundException
import com.example.kotlinquarkus.infra.PersonRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PersonService(
    private val personRepository: PersonRepository
) {

    fun getPersons(): Uni<List<Person>> {
        return personRepository.findAll()
    }

    fun getPerson(id: Long): Uni<Person> {
        return personRepository.findById(id)
            .onItem().ifNull().failWith(NotFoundException("Person with id $id not found"))
    }

    fun createPerson(person: Person): Uni<Person> = personRepository.save(person)

    fun updatePerson(id: Long, updatePerson: Person): Uni<Person> {
        return personRepository.save(id, updatePerson)
            .onItem().ifNull().failWith(NotFoundException("Person with id $id not found"))
    }
}