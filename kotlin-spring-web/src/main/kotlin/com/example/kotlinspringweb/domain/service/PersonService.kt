package com.example.kotlinspringweb.domain.service

import com.example.kotlinspringweb.infra.PersonRepository
import com.example.kotlinspringweb.domain.model.Person
import com.example.kotlinspringweb.domain.service.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personRepository: PersonRepository
) {

    fun getPersons(): List<Person> = personRepository.findAll()

    fun getPerson(id: Long): Person {
        return personRepository.findById(id)
            .orElseThrow { NotFoundException("Person with id $id not found") }
    }

    fun createPerson(person: Person): Person = personRepository.save(person)

    fun editPerson(id: Long, updatePerson: Person): Person {
        val person: Person = personRepository.findById(id)
           .orElseThrow { NotFoundException("Person with id $id not found") }

        val updatedPerson = person.copy(
           name = updatePerson.name,
           age = updatePerson.age
        )

        return personRepository.save(updatedPerson)

    }

}