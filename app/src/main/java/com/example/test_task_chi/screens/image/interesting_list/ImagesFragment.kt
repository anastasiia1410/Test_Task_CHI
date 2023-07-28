package com.example.test_task_chi.screens.image.interesting_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_chi.core.BaseFragment
import com.example.test_task_chi.databinding.FragmentImageBinding
import com.example.test_task_chi.screens.image.HomeViewModel

class ImagesFragment : BaseFragment<FragmentImageBinding>() {
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private val viewModel by viewModels<ImageViewModel>()
    private lateinit var adapter: ImageAdapter
    private lateinit var layoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ImageAdapter()
        layoutManager = LinearLayoutManager(requireContext())
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentImageBinding {
        return FragmentImageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageLayoutManager = GridLayoutManager(requireContext(), 2)
        with(binding) {
            rvRecycler.layoutManager = imageLayoutManager
            rvRecycler.adapter = adapter
        }

        viewModel.loadImages()

        viewModel.imageListLD.observe(viewLifecycleOwner) {
            adapter.updateItems(it)
        }

        binding.rvRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = imageLayoutManager.childCount
                val totalItemCount = imageLayoutManager.itemCount
                val firstVisibleItemPosition = imageLayoutManager.findFirstVisibleItemPosition()
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 1
                    && firstVisibleItemPosition >= 0
                ) {
                    viewModel.loadImages()
                }
            }
        })

        adapter.onFavoriteClick = { image ->
            homeViewModel.addToFavorite(image)
        }
    }
}