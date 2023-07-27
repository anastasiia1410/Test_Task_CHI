package com.example.test_task_chi.core

import android.app.Application
import android.content.Context
import com.example.test_task_chi.database.DatabaseManager

class App : Application() {
    lateinit var manager: DatabaseManager
        private set

    override fun onCreate() {
        super.onCreate()
        manager = DatabaseManager(this)
    }

    companion object {
        fun getInstance(context: Context): App = context.applicationContext as App
    }
}