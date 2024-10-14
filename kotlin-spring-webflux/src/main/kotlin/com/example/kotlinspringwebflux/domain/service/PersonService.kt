package com.example.kotlinspringwebflux.domain.service

import com.example.kotlinspringwebflux.domain.model.Person
import com.example.kotlinspringwebflux.infra.PersonRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PersonService(
    private val personRepository: PersonRepository
) {

    fun getPersons(): Flux<Person> = personRepository.findAll()

    fun getPerson(id: Long): Mono<Person> = personRepository.findById(id)

    fun createPerson(person: Person): Mono<Person> = personRepository.save(person)

    fun editPerson(id: Long, updatePerson: Person): Mono<Person> {
        return personRepository.findById(id)
            .flatMap { person ->
                val updatedPerson = person.copy(
                    name = updatePerson.name,
                    age = updatePerson.age
                )
                personRepository.save(updatedPerson)
            }

    }

}