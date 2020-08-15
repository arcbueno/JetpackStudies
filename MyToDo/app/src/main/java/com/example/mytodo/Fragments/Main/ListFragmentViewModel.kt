package com.example.mytodo.Fragments.Main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mytodo.Db.MyApplication
import com.example.mytodo.Db.TodoRepository
import com.example.mytodo.models.IListaTodo
import com.example.mytodo.models.Todo

class ListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: TodoRepository = TodoRepository(application)
    private var allTodos: LiveData<List<Todo>> = repository.getAllNotes()
    val listaTodo: ListaTodo = ListaTodo();

    fun getAllTodos(): LiveData<List<Todo>>{
        return allTodos
    }
}