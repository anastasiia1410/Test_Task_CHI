package com.example.test_task_chi.screens.image.interesting_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_chi.R
import com.example.test_task_chi.databinding.ItemImageBinding
import com.example.test_task_chi.screens.entity.Image
import com.example.test_task_chi.utils.loadImage

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.VH>() {
    private var imageList: List<Image> = mutableListOf()
    var onFavoriteClick: ((Image) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val image = imageList[position]
        with(holder.binding){
            ivImage.loadImage(image.imageUrl)
            ivFavorite.setImageResource(if (image.isFavorite) R.drawable.ic_favorite_enabled else R.drawable.ic_favorite)
            ivFavorite.setOnClickListener {
                onFavoriteClick?.invoke(image)
                notifyItemChanged(holder.adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int = imageList.size

    fun updateItems(newImageList: List<Image>) {
        imageList = newImageList
        notifyDataSetChanged()
    }


    class VH(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)
}