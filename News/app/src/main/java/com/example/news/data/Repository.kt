package com.example.news.data

import com.example.news.data.model.NewsData
import java.lang.Exception

class Repository {

    suspend fun getScienceNews(): NewsData? {
        val api = RetrofitInstance.buildApi(NewsApi::class.java)
        return try {
            api.getNewsData("science")
        } catch (e: Exception) {
            e
            null
        }
    }

}