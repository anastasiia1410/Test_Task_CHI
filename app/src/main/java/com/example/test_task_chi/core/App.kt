package com.example.test_task_chi.core

import android.app.Application
import android.content.Context
import com.example.test_task_chi.data.network.NetworkManager

class App : Application() {

    lateinit var networkManager: NetworkManager

    override fun onCreate() {
        super.onCreate()
        networkManager = NetworkManager()
    }

    companion object{
        fun getInstance(context: Context) : App = context.applicationContext as App
    }
}