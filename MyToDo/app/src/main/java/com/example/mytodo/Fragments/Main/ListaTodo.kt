package com.example.mytodo.Fragments.Main

import com.example.mytodo.Db.MyApplication
import com.example.mytodo.Db.TodoRepository
import com.example.mytodo.models.IListaTodo
import com.example.mytodo.models.Todo

class ListaTodo: IListaTodo {
    override fun update(todo: Todo){
        var repo = TodoRepository(MyApplication())
        repo.update(todo)
    }
}