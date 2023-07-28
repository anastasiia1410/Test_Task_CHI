package com.example.test_task_chi.screens.image

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.test_task_chi.screens.entity.Image

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val favoriteImagesLD = MutableLiveData<List<Image>>(emptyList())

    fun addToFavorite(image: Image) {
        image.isFavorite = !image.isFavorite
        val favoritesList = favoriteImagesLD.value ?: return
        if (image !in favoritesList) {
            favoriteImagesLD.postValue(favoritesList + image)
        } else {
            if (!image.isFavorite) {
                val updatedList = favoritesList.filter { it != image }
                favoriteImagesLD.postValue(updatedList)
            }
        }
    }
}
