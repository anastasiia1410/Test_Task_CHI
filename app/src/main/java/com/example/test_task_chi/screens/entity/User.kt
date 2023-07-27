package com.example.test_task_chi.screens.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long = 0,
    val name: String,
    val age: Int,
    val isStudent: Boolean = false,
) : Parcelable

