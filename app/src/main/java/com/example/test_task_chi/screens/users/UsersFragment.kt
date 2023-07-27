package com.example.test_task_chi.screens.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_task_chi.R
import com.example.test_task_chi.databinding.FragmentUsersBinding
import com.example.test_task_chi.screens.create_user.CreateUserFragment.Companion.CREATE_USER
import kotlinx.coroutines.launch

class UsersFragment : Fragment() {
    private lateinit var binding: FragmentUsersBinding
    private lateinit var adapter: UserAdapter
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = UserAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvRecycler.layoutManager = LinearLayoutManager(requireContext())
            rvRecycler.adapter = adapter

            setFragmentResultListener(CREATE_USER) { _, _ ->
                viewModel.getUsers()
            }

            bnBottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.add_new_user -> {
                        val action =
                            UsersFragmentDirections.actionUsersFragmentToCreateUserFragment()
                        findNavController().navigate(action)
                    }

                    else -> throw IllegalArgumentException()
                }
                return@setOnItemSelectedListener true
            }
        }

        adapter.onItemClick = { user ->
            val action = UsersFragmentDirections.actionUsersFragmentToUserDetailFragment(user)
            findNavController().navigate(action)

        }

        adapter.onSwitcherChange = { id, isStudent ->
            viewModel.changedStudentStatus(id, isStudent)
        }

        lifecycleScope.launch {
            viewModel.usersFlow.collect {
                adapter.updateItems(it)
            }
        }
    }
}