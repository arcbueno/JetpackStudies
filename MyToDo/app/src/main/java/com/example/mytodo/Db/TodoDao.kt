package com.example.mytodo.Db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytodo.models.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos") fun getAllTodos(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertTodo(vararg todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Delete fun deleteTodo(todo: Todo)

    @Query("Select * from todos where todos.id = :id" ) fun getById(id:Int):Todo

}