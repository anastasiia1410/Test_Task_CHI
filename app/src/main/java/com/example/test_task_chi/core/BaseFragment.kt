package com.example.test_task_chi.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding ?: error("binding isn`t set")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = createBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    protected abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    protected open fun addWindowInsets(
        view: View,
        @WindowInsetsCompat.Type.InsetsType type: Int = WindowInsetsCompat.Type.systemBars(),
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v: View, insets: WindowInsetsCompat ->
            val systemBarInsets = insets.getInsets(type)
            v.setPadding(
                systemBarInsets.left,
                systemBarInsets.top,
                systemBarInsets.right,
                systemBarInsets.bottom
            )
            insets
        }
    }
}