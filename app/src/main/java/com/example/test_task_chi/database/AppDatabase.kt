package com.example.test_task_chi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test_task_chi.database.dao.UserDao
import com.example.test_task_chi.database.entity.UserDatabase

@Database(
    entities = [
        UserDatabase::class
    ],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
