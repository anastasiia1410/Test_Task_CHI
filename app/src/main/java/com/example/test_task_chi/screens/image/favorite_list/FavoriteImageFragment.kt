package com.example.test_task_chi.screens.image.favorite_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_task_chi.core.BaseFragment
import com.example.test_task_chi.databinding.FragmentFavoriteImageBinding
import com.example.test_task_chi.screens.image.HomeViewModel

class FavoriteImageFragment : BaseFragment<FragmentFavoriteImageBinding>() {
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var adapter: FavoriteImageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FavoriteImageAdapter()
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentFavoriteImageBinding {
        return FragmentFavoriteImageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
            rvRecycler.adapter = adapter
        }

        homeViewModel.favoriteImagesLD.observe(viewLifecycleOwner) {
            adapter.updateItems(it)
        }
    }
}