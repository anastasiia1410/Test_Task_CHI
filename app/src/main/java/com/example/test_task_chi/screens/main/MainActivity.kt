package com.example.test_task_chi.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test_task_chi.R
import com.example.test_task_chi.databinding.ActivityMainBinding
import com.example.test_task_chi.screens.image.AnimalsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.fcMain, AnimalsFragment()).commit()
    }
}