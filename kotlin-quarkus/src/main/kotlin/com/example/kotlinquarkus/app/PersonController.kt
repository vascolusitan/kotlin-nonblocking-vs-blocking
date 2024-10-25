package com.example.kotlinquarkus.app

import com.example.kotlinquarkus.domain.model.Person
import com.example.kotlinquarkus.domain.service.PersonService
import io.smallrye.mutiny.Uni
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/api/v1/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PersonController(
    private val personService: PersonService
) {

    @GET
    @Path("/hello")
    fun hello(): String {
        return "Hello, Quarkus!"
    }

    @GET
    fun getPersons(): Uni<Response> {
        return personService.getPersons()
            .map { persons -> Response.ok(persons).build() }
    }

    @PUT
    @Path("/{id}")
    fun updatePerson(@PathParam("id") id: Long, person: Person): Uni<Response> {
        return personService.updatePerson(id, person)
            .map { personRes -> Response.ok(personRes).build() }
    }

}