package com.example.test_task_chi.screens.user_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_chi.core.App
import com.example.test_task_chi.database.DatabaseManager
import com.example.test_task_chi.screens.entity.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private var databaseManager: DatabaseManager
    val userFlow = MutableSharedFlow<User>()

    init {
        databaseManager = App.getInstance(application).manager
    }

    fun getUser(id : Long) = viewModelScope.launch {
       val user = databaseManager.getUserById(id)
        userFlow.emit(user)
    }
}