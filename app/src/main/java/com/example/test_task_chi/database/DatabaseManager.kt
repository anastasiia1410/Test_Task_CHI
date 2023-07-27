package com.example.test_task_chi.database

import android.content.Context
import androidx.room.Room
import com.example.test_task_chi.database.entity.toUser
import com.example.test_task_chi.database.entity.toUserDatabase
import com.example.test_task_chi.screens.entity.User

class DatabaseManager(context: Context) {

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "users.sqlite"
    ).build()

    suspend fun changeStudentStatus(id: Long, isStudent: Boolean) {
        db.userDao().changeStudentStatus(id, isStudent)
    }

    suspend fun getUserById(id: Long): User {
        return db.userDao().getUserById(id).toUser()
    }

    suspend fun insertUser(user: User) {
        db.userDao().insertUser(user.toUserDatabase())
    }

    suspend fun getUsers(): List<User> {
        return db.userDao().getAllUsers().map { it.toUser() }
    }
}