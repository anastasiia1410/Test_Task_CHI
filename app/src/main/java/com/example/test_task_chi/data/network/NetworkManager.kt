package com.example.test_task_chi.data.network

import com.example.test_task_chi.data.network.entity.ImageNetwork
import com.example.test_task_chi.data.network.entity.toImage
import com.example.test_task_chi.screens.entity.Image
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager {
    private val gson: Gson
    private val client: OkHttpClient
    private val retrofit: Retrofit
    private val api: Api

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        gson = GsonBuilder().serializeNulls().create()
        retrofit = Retrofit.Builder()
            .baseUrl("https://shibe.online/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(Api::class.java)
    }

    suspend fun load(count: Int): List<Image> {
        val responseBody: ResponseBody = api.load(count)
        val jsonArray = JSONArray(responseBody.string())
        val imageList = mutableListOf<ImageNetwork>()
        for (i in 0 until jsonArray.length()) {
            val imageUrl = jsonArray.getString(i)
            val image = ImageNetwork(imageUrl = imageUrl)
            imageList.add(image)
        }
        return imageList.map { it.toImage() }
    }
}