package com.example.mytodo.Fragments.AddTodo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mytodo.Db.TodoRepository
import com.example.mytodo.models.Todo

class AddTodoViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: TodoRepository = TodoRepository(application)

    fun insert(todo: Todo){
        repository.insert(todo)
    }

}