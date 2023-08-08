package com.furkan.mvvm_news_app.repository

import com.furkan.mvvm_news_app.data.remote.NewsApi
import com.furkan.mvvm_news_app.data.remote.responses.NewsResponse
import com.furkan.mvvm_news_app.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class NewsRepository @Inject constructor(
    private val api: NewsApi
) {
    suspend fun searchForNews(searchQuery: String, pageNumber: Int): Resource<NewsResponse> {
        val response = try {
            api.getNews(searchQuery, pageNumber)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(message = e.localizedMessage!!)
        }
        return Resource.Success(data = response)
    }

}