package com.example.test_task_chi.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test_task_chi.screens.entity.User

@Entity(tableName = "user")
data class UserDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val age: Int,
    val isStudent: Boolean,
)

fun User.toUserDatabase(): UserDatabase {
    return UserDatabase(
        id = this.id,
        name = this.name,
        age = this.age,
        isStudent = this.isStudent
    )
}

fun UserDatabase.toUser(): User {
    return User(
        id = this.id,
        name = this.name,
        age = this.age,
        isStudent = this.isStudent
    )
}