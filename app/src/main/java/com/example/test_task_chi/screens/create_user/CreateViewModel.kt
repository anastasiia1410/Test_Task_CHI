package com.example.test_task_chi.screens.create_user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_chi.core.App
import com.example.test_task_chi.database.DatabaseManager
import com.example.test_task_chi.screens.entity.User
import com.example.test_task_chi.utils.parseDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class CreateViewModel(application: Application) : AndroidViewModel(application) {
    private var databaseManager: DatabaseManager
    val isLoadedFlow = MutableStateFlow(false)

    init {
        databaseManager = App.getInstance(application).manager
    }

    fun insertUser(name: String, age: String) = viewModelScope.launch {
        databaseManager.insertUser(
            User(
                name = name,
                age = age.calculateAge()
            )
        )
        isLoadedFlow.emit(true)
    }

    private fun String.calculateAge(): Int {
        val date = Calendar.getInstance().also { it.time = this.parseDate() }
        val now = Calendar.getInstance()
        val currentYear = now.get(Calendar.YEAR)
        val birthYear = date.get(Calendar.YEAR)
        val isBirthPassed = (date.get(Calendar.DAY_OF_YEAR) >= now.get(Calendar.DAY_OF_YEAR))

        return if (isBirthPassed) {
            currentYear - birthYear
        } else {
            currentYear - birthYear - 1
        }
    }
}
