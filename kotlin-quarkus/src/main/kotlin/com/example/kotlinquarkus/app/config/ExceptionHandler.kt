package com.example.kotlinquarkus.app.config

import com.example.kotlinquarkus.domain.service.exception.NotFoundException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class ExceptionHandler : ExceptionMapper<NotFoundException> {

    override fun toResponse(exception: NotFoundException): Response {
        return Response.status(Response.Status.NOT_FOUND)
            .entity(mapOf("error" to exception.message))
            .build()
    }

}
