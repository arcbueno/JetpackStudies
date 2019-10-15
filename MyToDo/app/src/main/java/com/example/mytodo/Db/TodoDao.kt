package com.example.mytodo.Db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.mytodo.Models.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos") fun getAllTodos(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertTodo(vararg todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Delete fun deleteTodo(todo: Todo)

}