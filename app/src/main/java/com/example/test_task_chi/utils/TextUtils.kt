package com.example.test_task_chi.utils

import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun TextView?.inputText(): String {
    return this?.text?.toString() ?: ""
}

fun String.parseDate(): Date {
    val formattedDate = this.replace("-", "/")
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return simpleDateFormat.parse(formattedDate) ?: throw Exception()
}

