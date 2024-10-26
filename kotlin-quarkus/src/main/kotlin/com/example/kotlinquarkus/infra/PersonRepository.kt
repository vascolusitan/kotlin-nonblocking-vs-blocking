package com.example.kotlinquarkus.infra

import com.example.kotlinquarkus.domain.model.Person
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PersonRepository(
    private val panacheRepository: PanacheRepository
) {

    @WithSession
    fun findById(id: Long): Uni<Person> = panacheRepository.findById(id)

    @WithSession
    fun findAll(): Uni<List<Person>> {
        return panacheRepository.listAll()
    }

    @WithTransaction
    fun save(person: Person): Uni<Person> = panacheRepository.persist(person)

    @WithTransaction
    fun save(id: Long, updatePerson: Person): Uni<Person> {
        return panacheRepository.findById(id)
            .onItem().ifNotNull().invoke {
                    person ->
                    person.name = updatePerson.name
                    person.age = updatePerson.age
            }
    }

}