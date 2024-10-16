package com.example.kotlinspringweb.app

import com.example.kotlinspringweb.domain.model.Person
import com.example.kotlinspringweb.domain.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/persons")
class PersonController(
    private val personService: PersonService
) {

    @GetMapping
    fun getPersons(): List<Person> = personService.getPersons()

    @GetMapping("/{id}")
    fun getPersonById(@PathVariable id: Long): Person {
        return personService.getPerson(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPerson(person: Person): Person = personService.createPerson(person)

    @PutMapping("/{id}")
    fun editPerson(@PathVariable id: Long, person: Person): Person {
        return personService.editPerson(id, person)
    }

}