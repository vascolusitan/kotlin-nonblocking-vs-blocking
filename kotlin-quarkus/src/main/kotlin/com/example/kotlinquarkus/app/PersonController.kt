package com.example.kotlinquarkus.app

import com.example.kotlinquarkus.domain.model.Person
import com.example.kotlinquarkus.domain.service.PersonService
import io.smallrye.mutiny.Uni
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
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
    fun hello(): Uni<String> = Uni.createFrom().item("Hello, Quarkus!")

    @GET
    fun getPersons(): Uni<Response> {
        return personService.getPersons()
            .onItem().transform { personList ->
                Response.ok(personList).build()
            }
    }

    @GET
    @Path("/{id}")
    fun getPerson(@PathParam("id") id: Long): Uni<Person> = personService.getPerson(id)

    @POST
    fun createPerson(person: Person): Uni<Response> {
        return personService.createPerson(person)
            .onItem().transform { createdPerson ->
                Response.created(null).entity(createdPerson).build()
            }
    }

    @PUT
    @Path("/{id}")
    fun updatePerson(@PathParam("id") id: Long, person: Person): Uni<Response> {
        return personService.updatePerson(id, person)
            .onItem().transform { updatedPerson ->
                Response.ok(updatedPerson).build()
            }
    }

}