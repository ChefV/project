package com.example.news.data

import com.example.news.data.model.NewsData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {

    @Headers("Content-Type: application/json")
    @GET("news")
    suspend fun getNewsData(@Query("category") category: String): NewsData

}