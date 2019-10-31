package com.example.mytodo.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mytodo.Db.TodoRepository

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: TodoRepository = TodoRepository(application)

    private var allTodos: LiveData<List<Todo>> = repository.getAllNotes()


    fun insert(todo: Todo){
        repository.insert(todo)
    }

    fun getAllTodos(): LiveData<List<Todo>>{
        return allTodos
    }

    fun getById(id:Int): Todo{
        return repository.getById(id)
    }

    fun delete(todo: Todo){
        repository.delete(todo)
    }

    fun update(todo:Todo){
        repository.update(todo)
    }

}