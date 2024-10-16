package com.example.kotlinspringweb.app.config

import com.example.kotlinspringweb.domain.service.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<String> =
        ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
}
