package com.example.test_task_chi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test_task_chi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(BUNDLE_KEY)
        }
        with(binding) {
            tvCounter.text = counter.toString()
            btButton.setOnClickListener {
                counter++
                tvCounter.text = counter.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BUNDLE_KEY, counter)
    }

    companion object {
        const val BUNDLE_KEY = "counter"
    }
}