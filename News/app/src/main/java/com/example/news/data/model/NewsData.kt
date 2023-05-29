package com.example.news.data.model


import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("category")
    val category: String?,
    @SerializedName("data")
    val data: List<Data?>?,
    @SerializedName("success")
    val success: Boolean?
)