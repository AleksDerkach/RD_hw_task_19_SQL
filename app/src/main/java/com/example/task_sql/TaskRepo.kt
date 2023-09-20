package com.example.task_sql

import java.util.concurrent.Executors

class TaskRepo(private val db: TaskDataBase) {
    private val executor = Executors.newSingleThreadExecutor()

    fun getAll() = db.taskDao().getAll()

    fun add(task: Task){
        executor.execute { db.taskDao().addTask(task) }
    }

    fun remove(task: Task) {
        executor.execute {
            db.taskDao().delete(task)
        }
    }

    fun update(task: Task){
        executor.execute {
            db.taskDao().update(task)
        }
    }
}