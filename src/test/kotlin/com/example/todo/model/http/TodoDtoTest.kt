package com.example.todo.model.http

import com.example.todo.controller.api.todo.model.http.TodoDto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.validation.FieldError
import javax.validation.Validation

class TodoDtoTest {

    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest() {
        val todoDto = TodoDto().apply {
            this.title = "test"
            this.description = "test"
            this.schedule = "2022-12-02 14:27:25"
        }

        val result = validator.validate(todoDto)
        Assertions.assertEquals(true, result.isEmpty())
    }


}