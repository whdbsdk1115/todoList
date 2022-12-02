package com.example.todo.repository

import com.example.todo.database.Todo

interface TodoRepository {

    fun save(todo: Todo): Todo // 저장 : 저장된 Todo return
    fun saveAll(todoList: MutableList<Todo>): Boolean // 리스트 형식으로 저장 : Boolean으로 결과만 알려줌

    fun update(todo: Todo): Todo // 업데이트: Todo return
    fun delete(index: Int): Boolean // 삭제(인덱스 이용) : 불리언

    fun findOne(index: Int): Todo? // 찾기
    fun findAll(): MutableList<Todo>

}