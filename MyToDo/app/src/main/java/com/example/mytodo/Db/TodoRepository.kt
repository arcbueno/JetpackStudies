package com.example.mytodo.Db

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytodo.Fragments.listaTodo
import com.example.mytodo.Models.Todo

class TodoRepository(application: Application) {

    private var todoDao: TodoDao

    private var allTodos: LiveData<List<Todo>>

    init {
        val database = MyApplication.database!!
        todoDao = database.todoDao()
        allTodos = database.todoDao().getAllTodos()

    }

    fun insert(todo: Todo){
        val insertTodoAsyncTask = InsertTodoAsyncTask(todoDao).execute(todo)
    }


    fun update(todo:Todo){
        val updateTodoAsyncTask = UpdateTodoAsyncTask(todoDao).execute(todo)
    }

    fun delete(todo: Todo){
        val deleteTodoAsyncTask = DeleteTodoAsyncTask(todoDao).execute(todo)
    }

    fun getAllNotes(): LiveData<List<Todo>> {
        return allTodos
    }

    fun getById(id:Int): Todo{
        return todoDao.getById(id)
    }

    private class InsertTodoAsyncTask(todoDao:TodoDao) : AsyncTask<Todo, Unit, Unit>() {
        val todoDao = todoDao

        override fun doInBackground(vararg p0: Todo?) {
            todoDao.insertTodo(p0[0]!!)
        }
    }

    private class UpdateTodoAsyncTask(todoDao:TodoDao) : AsyncTask<Todo, Unit, Unit>() {
        val todoDao = todoDao

        override fun doInBackground(vararg p0: Todo?) {
            todoDao.updateTodo(p0[0]!!)
        }
    }

    private class DeleteTodoAsyncTask(todoDao: TodoDao) : AsyncTask<Todo, Unit, Unit>(){

        val todoDao = todoDao

        override fun doInBackground(vararg p0: Todo?) {
            todoDao.deleteTodo(p0[0]!!)
        }

    }

}