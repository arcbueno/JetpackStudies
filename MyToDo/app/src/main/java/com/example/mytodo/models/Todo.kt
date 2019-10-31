package com.example.mytodo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true) val Id: Int?,
    @ColumnInfo(name = "title") val Title: String,
    @ColumnInfo(name = "texto") val Text: String,
    @ColumnInfo(name = "checked") var Checked: Boolean
)



