package com.example.todo.controller.api.todo

import com.example.todo.controller.api.todo.model.http.TodoDto
import com.example.todo.controller.api.todo.service.TodoService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/todo")
class TodoApiController(val todoService: TodoService) {

    // R
    @GetMapping(path = [""])
    fun read(@RequestParam(required = false) index: Int?): ResponseEntity<Any?> { // 필수값 x, 있으면 단독조회 없으면 전체조회

        return index?.let { // 인덱스 유
            todoService.read(it)
        }?.let {
            return ResponseEntity.ok(it)
        }?: kotlin.run {    // 인덱스 무
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY) // 302 에러
                    .header(HttpHeaders.LOCATION, "/api/todo")
                    .build()
        }
    }

    // R
    @GetMapping(path = ["/all"])
    fun readAll(): MutableList<TodoDto> { // 필수값 x, 있으면 단독조회 없으면 전체조회
        return todoService.readAll()

    }


    // C
    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto) // responseEntity로 바꿔야 함
    }


    // U TODO create 201, update 200로 변경
    @PutMapping(path = [""])
    fun update(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)

    }


    // D
    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name = "index") _index: Int): ResponseEntity<Any> {

        if (todoService.delete(_index)) {
            return ResponseEntity.status(500).build()
        }
        return ResponseEntity.ok().build()
    }
}