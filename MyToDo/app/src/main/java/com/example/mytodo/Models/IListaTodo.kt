package com.example.mytodo.Models

import com.example.mytodo.Db.MyApplication
import com.example.mytodo.Db.TodoRepository

interface IListaTodo {

    fun update(todo:Todo)

}