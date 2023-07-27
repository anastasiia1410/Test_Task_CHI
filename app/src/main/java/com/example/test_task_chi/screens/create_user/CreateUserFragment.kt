package com.example.test_task_chi.screens.create_user

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test_task_chi.databinding.FragmentCreateUserBinding
import com.example.test_task_chi.utils.inputText
import kotlinx.coroutines.launch

class CreateUserFragment : Fragment() {
    private lateinit var binding: FragmentCreateUserBinding
    private val viewModel by viewModels<CreateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.isLoadedFlow.collect {
                if (it) {
                    setFragmentResult(CREATE_USER, Bundle.EMPTY)
                    findNavController().popBackStack()
                }
            }
        }

        with(binding) {
            etAge.setOnClickListener { showDatePicker() }

            btSave.setOnClickListener {
                if (binding.etName.inputText().isNotEmpty() && binding.etAge.inputText()
                        .isNotEmpty()
                ) {
                    viewModel.insertUser(binding.etName.inputText(), binding.etAge.inputText())
                } else {
                    Toast.makeText(requireContext(), "Fill in all the fields", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun showDatePicker() {
        with(DatePickerDialog(requireContext())) {
            setOnDateSetListener { _, year, month, dayOfMonth ->
                val str = "$dayOfMonth-${month + 1}-$year"
                binding.etAge.setText(str)
            }
            show()
        }
    }

    companion object {
        const val CREATE_USER = "result"
    }
}