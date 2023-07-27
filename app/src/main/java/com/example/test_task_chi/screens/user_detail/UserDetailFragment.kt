package com.example.test_task_chi.screens.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.test_task_chi.databinding.FragmentUserDetailBinding
import kotlinx.coroutines.launch

class UserDetailFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailBinding
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = UserDetailFragmentArgs.fromBundle(requireArguments())
        viewModel.getUser(args.user.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.userFlow.collect { user ->
                binding.apply {
                    tvUserName.text = user.name
                    tvAge.text = user.age.toString()
                    switcher.isChecked = user.isStudent
                    switcher.isEnabled = false
                }
            }
        }
    }
}