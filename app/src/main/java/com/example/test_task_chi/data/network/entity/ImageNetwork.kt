package com.example.test_task_chi.data.network.entity

import com.example.test_task_chi.screens.entity.Image

data class ImageNetwork(val imageUrl: String)

fun ImageNetwork.toImage(): Image {
    return Image(
        imageUrl = this.imageUrl,
        isFavorite = false
    )
}
