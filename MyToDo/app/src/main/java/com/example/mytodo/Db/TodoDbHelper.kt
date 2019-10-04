package com.example.mytodo.Db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.mytodo.Models.Todo

class TodoDbHelper(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun InsertTodo(todo: Todo):Boolean {
        val db = writableDatabase

        val values = ContentValues()
        values.put(DbContract.TodoEntry.COLUMN_TITLE, todo.Title)
        values.put(DbContract.TodoEntry.COLUMN_TEXT, todo.Text)
        var checkedInt = if (todo.Checked) 1 else 0
        values.put(DbContract.TodoEntry.COLUMN_CHECK, checkedInt)
        val newRowId = db.insert(DbContract.TodoEntry.TABLE_NAME, null, values)
        db.close()

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteTodo(id: Int): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DbContract.TodoEntry.COLUMN_ID + " LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(id.toString())
        // Issue SQL statement.
        db.delete(DbContract.TodoEntry.TABLE_NAME, selection, selectionArgs)

        db.close()
        return true
    }

    fun dropTable(){
        var cursor : Cursor? = null
        val db = writableDatabase
        try{
            db.execSQL(SQL_DELETE_ENTRIES)
        }catch (e: SQLiteException){

        }

        db.close()
    }


    fun readTodo(id:Int):ArrayList<Todo>{
        val todos = ArrayList<Todo>()
        val db = writableDatabase;
        var cursor : Cursor? = null

        try{
            cursor = db.rawQuery(
                "select * from " + DbContract.TodoEntry.TABLE_NAME
                        +" where "+ DbContract.TodoEntry.COLUMN_ID +
                        " = '" + id +"'", null)
        }catch (e: SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var title: String
        var text: String
        var checked : Boolean

        if(cursor!!.moveToFirst()){
            while(cursor.isAfterLast == false){
                title = cursor.getString(cursor.getColumnIndex(DbContract.TodoEntry.COLUMN_TITLE))
                text = cursor.getString(cursor.getColumnIndex(DbContract.TodoEntry.COLUMN_TEXT))
                checked = cursor?.getString(cursor.getColumnIndex(DbContract.TodoEntry.COLUMN_CHECK))
                    .toBoolean()

                todos.add(Todo(id,title, text, checked))
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return todos
    }

    fun readAllTodos():ArrayList<Todo>{
        val todos = ArrayList<Todo>()
        val db = readableDatabase
        var cursor: Cursor?

        try{
            cursor = db.rawQuery("select * from " + DbContract.TodoEntry.TABLE_NAME, null)
        }catch (e:SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var id:Int
        var title:String
        var text:String
        var checked:Boolean
        if(cursor!!.moveToFirst()){
            while (!cursor.isAfterLast){
                id = cursor.getInt(cursor.getColumnIndex(DbContract.TodoEntry.COLUMN_ID))
                title = cursor.getString(cursor.getColumnIndex(DbContract.TodoEntry.COLUMN_TITLE))
                text = cursor.getString(cursor.getColumnIndex(DbContract.TodoEntry.COLUMN_TEXT))
                var checkedInt = cursor.getInt(cursor.getColumnIndex(DbContract.TodoEntry.COLUMN_CHECK))
                checked = checkedInt == 1
                cursor.moveToNext()
                todos.add(Todo(id, title, text, checked))
            }
        }

        cursor.close()
        db.close()
        return todos
    }

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "TodoList.db"

        private val SQL_CREATE_ENTRIES = "CREATE TABLE "+ DbContract.TodoEntry.TABLE_NAME + " ("+
                DbContract.TodoEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                DbContract.TodoEntry.COLUMN_TITLE + " TEXT, " +
                DbContract.TodoEntry.COLUMN_TEXT + " TEXT, " +
                DbContract.TodoEntry.COLUMN_CHECK + " INTEGER)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+ DbContract.TodoEntry.TABLE_NAME
    }

}