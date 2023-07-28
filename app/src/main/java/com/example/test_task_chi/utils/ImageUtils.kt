package com.example.test_task_chi.utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String) {
    Glide.with(this).load(url).fitCenter().centerCrop().into(this)
}