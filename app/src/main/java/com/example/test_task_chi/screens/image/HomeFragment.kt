package com.example.test_task_chi.screens.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.test_task_chi.R
import com.example.test_task_chi.core.BaseFragment
import com.example.test_task_chi.databinding.FragmentHomeBinding
import com.example.test_task_chi.screens.image.Page.Companion.pageByPosition
import com.example.test_task_chi.screens.image.favorite_list.FavoriteImageFragment
import com.example.test_task_chi.screens.image.interesting_list.ImagesFragment


class AnimalsFragment : BaseFragment<FragmentHomeBinding>() {
    private val callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bnBottomNavigation.selectedItemId = pageByPosition(position).menuId

        }
    }
    private lateinit var adapter: AdapterViewPager

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AdapterViewPager(
            items = listOf(Page.Image, Page.Favorite),
            fragmentManager = childFragmentManager,
            lifecycle = lifecycle
        )

        with(binding) {
            vpViewPager.adapter = adapter
            bnBottomNavigation.setOnItemSelectedListener { item ->
                val page = Page.pageById(item.itemId)
                vpViewPager.currentItem = page.position
                return@setOnItemSelectedListener true
            }
        }

    }

    override fun onStart() {
        super.onStart()
        binding.vpViewPager.registerOnPageChangeCallback(callback)
    }

    override fun onStop() {
        super.onStop()
        binding.vpViewPager.unregisterOnPageChangeCallback(callback)
    }
}

sealed class Page(val position: Int, val menuId: Int) {
    object Image : Page(0, R.id.page_interesting)
    object Favorite : Page(1, R.id.page_favorite)

    companion object {
        fun pageByPosition(position: Int): Page {
            return when (position) {
                Image.position -> Image
                Favorite.position -> Favorite
                else -> throw IllegalArgumentException()
            }
        }

        fun pageById(menuId: Int): Page {
            return when (menuId) {
                Image.menuId -> Image
                Favorite.menuId -> Favorite
                else -> throw IllegalArgumentException()
            }
        }
    }
}

class AdapterViewPager(
    private val items: List<Page>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        return when (items[position]) {
            Page.Image -> ImagesFragment()
            Page.Favorite -> FavoriteImageFragment()
        }
    }
}