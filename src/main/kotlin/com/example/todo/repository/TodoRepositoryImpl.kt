package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl: TodoRepository {

    @Autowired // 자동 주입/접근
    lateinit var todoDataBase: TodoDataBase

    override fun save(todo: Todo): Todo {

        return todo.apply {
            this.index = ++todoDataBase.index // 마지막 인덱스에서 +1한 값으로 적용.
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }.run {
            todoDataBase.todoList.add(todo)
            this
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {

        return try {
            todoList.forEach {
                save(it)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun update(todo: Todo): Todo {
        TODO("Not yet implemented")
    }

    override fun delete(index: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun findOne(index: Int): Todo? {
        return todoDataBase.todoList.first { it.index == index }
    }

    override fun findAll(): MutableList<Todo> {
        TODO("Not yet implemented")
    }
}