package com.example.mytodo.Db

import android.app.Application
import androidx.room.Room
import com.facebook.stetho.Stetho

open class MyApplication : Application(){

    companion object {
        var database: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()

        //Room
        database = Room.databaseBuilder(this, AppDataBase::class.java, "todos").allowMainThreadQueries().build()

        //Stetho
        val initializerBuilder = Stetho.newInitializerBuilder(this)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        val initializer = initializerBuilder.build()
        Stetho.initialize(initializer)
    }
}