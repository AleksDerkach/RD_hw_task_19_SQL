package com.example.task_sql

import android.widget.TextView
import androidx.room.Entity
import androidx.room.PrimaryKey

// Таблиці
@Entity (tableName = "task")
data class Task(@PrimaryKey (autoGenerate = true) val id: Int? = null,
                val task:String, val doneFlag:Boolean)
