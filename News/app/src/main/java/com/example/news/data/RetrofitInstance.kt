package com.example.news.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().build()

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://inshorts.deta.dev/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()


    fun <T> buildApi(service: Class<T>): T {
        return retrofit.create(service)
    }

}