package com.example.test_task_chi.screens.image.interesting_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test_task_chi.core.App
import com.example.test_task_chi.data.network.NetworkManager
import com.example.test_task_chi.screens.entity.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel(application: Application) : AndroidViewModel(application) {
    private var networkManager: NetworkManager
    val imageListLD = MutableLiveData<List<Image>>()
    private var count: Int = 10

    init {
        networkManager = App.getInstance(application).networkManager
    }

    fun loadImages() = viewModelScope.launch(Dispatchers.IO) {
        val newList = networkManager.load(count)
        val oldList = imageListLD.value
        imageListLD.postValue(if (oldList != null) oldList + newList else newList)
    }
}