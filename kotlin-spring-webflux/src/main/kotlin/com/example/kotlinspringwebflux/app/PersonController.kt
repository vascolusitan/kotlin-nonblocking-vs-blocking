package com.example.kotlinspringwebflux.app

import com.example.kotlinspringwebflux.domain.model.Person
import com.example.kotlinspringwebflux.domain.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/persons")
class PersonController(
    private val personService: PersonService
) {

    @GetMapping("/hello")
    fun hello(): Mono<String> = Mono.just("Hello, Spring Webflux!")

    @GetMapping
    fun getPersons(): Flux<Person> = personService.getPersons()

    @GetMapping("/{id}")
    fun getPersonById(@PathVariable id: Long): Mono<Person> {
        return personService.getPerson(id)
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND)))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPerson(@RequestBody person: Person): Mono<Person> = personService.createPerson(person)

    @PutMapping("/{id}")
    fun editPerson(@PathVariable id: Long, @RequestBody person: Person): Mono<Person> {
        return personService.editPerson(id, person)
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND)))
    }

}