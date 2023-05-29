package com.example.news.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.Repository
import com.example.news.data.model.NewsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    val newsLiveData = MutableLiveData<NewsData?>()

    fun getNews() {
        val repository = Repository()

        viewModelScope.launch(Dispatchers.IO) {
            newsLiveData.postValue(repository.getScienceNews())
        }
    }

}