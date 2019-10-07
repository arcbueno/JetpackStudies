package com.example.mytodo.Db

import androidx.room.*
import com.example.mytodo.Models.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos") fun getAllTodos(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertTodo(vararg todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Delete fun deleteTodo(todo: Todo)

//    @Query ("DROP TABLE todos") fun dropTable()

}