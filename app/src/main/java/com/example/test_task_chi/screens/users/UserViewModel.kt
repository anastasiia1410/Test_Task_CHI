package com.example.test_task_chi.screens.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_chi.core.App
import com.example.test_task_chi.database.DatabaseManager
import com.example.test_task_chi.screens.entity.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var databaseManager: DatabaseManager
    val usersFlow = MutableSharedFlow<List<User>>()

    init {
        databaseManager = App.getInstance(application).manager
        getUsers()
    }

    fun changedStudentStatus(id: Long, isStudent: Boolean) = viewModelScope.launch {
        databaseManager.changeStudentStatus(id, isStudent)
    }

    fun getUsers() = viewModelScope.launch {
        val usersList = databaseManager.getUsers()
        usersFlow.emit(usersList)
    }
}
