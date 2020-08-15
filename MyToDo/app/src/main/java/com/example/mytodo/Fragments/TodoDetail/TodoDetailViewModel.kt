package com.example.mytodo.Fragments.TodoDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mytodo.Db.TodoRepository
import com.example.mytodo.models.Todo

class TodoDetailViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository = TodoRepository(application)

    fun delete(todo: Todo){
        repository.delete(todo)
    }

    fun getById(id:Int): Todo {
        return repository.getById(id)
    }

    fun insert(todo: Todo){
        repository.insert(todo)
    }
}