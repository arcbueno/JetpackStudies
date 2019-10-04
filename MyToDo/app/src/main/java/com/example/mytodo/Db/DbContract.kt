package com.example.mytodo.Db

import android.provider.BaseColumns

object DbContract {

    class TodoEntry: BaseColumns{
        companion object{
            val TABLE_NAME = "todos"
            val COLUMN_ID = "id"
            val COLUMN_TITLE = "title"
            val COLUMN_TEXT = "texto"
            val COLUMN_CHECK = "checked"
        }
    }

}