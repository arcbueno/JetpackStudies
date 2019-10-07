package com.example.mytodo.Db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytodo.Models.Todo

@Database(version = 1, entities = arrayOf(Todo::class))
abstract class AppDataBase :RoomDatabase(){

    abstract fun todoDao(): TodoDao

}