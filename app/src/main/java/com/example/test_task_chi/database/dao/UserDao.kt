package com.example.test_task_chi.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.test_task_chi.database.entity.UserDatabase

@Dao
interface UserDao {

    @Query("UPDATE user SET isStudent = :isStudent WHERE id = :id")
    suspend fun changeStudentStatus(id: Long, isStudent: Boolean)

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Long): UserDatabase

    @Insert
    suspend fun insertUser(userDatabase: UserDatabase)

    @Query("SELECT * FROM user")
   suspend fun getAllUsers(): List<UserDatabase>

}