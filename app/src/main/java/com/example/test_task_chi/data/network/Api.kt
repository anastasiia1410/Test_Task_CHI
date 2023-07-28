package com.example.test_task_chi.data.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("shibes")
    suspend fun load(
        @Query("count") count: Int?,
        @Query("urls") urls: Boolean = true,
        @Query("httpsUrls") httpsUrls: Boolean = true,
    ): ResponseBody
}