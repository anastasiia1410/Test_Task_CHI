package com.example.test_task_chi.screens.image.favorite_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_chi.databinding.ItemFavoriteBinding
import com.example.test_task_chi.screens.entity.Image
import com.example.test_task_chi.utils.loadImage

class FavoriteImageAdapter : RecyclerView.Adapter<FavoriteImageAdapter.VH>() {
    private var favoriteImageList: List<Image> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val image = favoriteImageList[position]
        holder.binding.ivImage.loadImage(image.imageUrl)
    }

    override fun getItemCount(): Int = favoriteImageList.size

    fun updateItems(newImageList: List<Image>) {
        favoriteImageList = newImageList
        notifyDataSetChanged()
    }

    class VH(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

}