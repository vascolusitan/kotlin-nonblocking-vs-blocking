package com.example.kotlinspringweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringWebApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringWebApplication>(*args)
}
