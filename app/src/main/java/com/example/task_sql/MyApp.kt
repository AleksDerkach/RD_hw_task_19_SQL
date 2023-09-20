package com.example.task_sql

import android.app.Application
import androidx.room.Room

class MyApp : Application() {
   lateinit var repo: TaskRepo
    override fun onCreate() {
        super.onCreate()
        instance = this
        val db = Room.databaseBuilder(context = this,
            klass = TaskDataBase::class.java,
            name = "task_db")
            .build()
        repo = TaskRepo(db)
    }

    companion object {
        private lateinit var instance:MyApp
        fun getApp() = instance
    }
}