package com.furkan.mvvm_news_app.repository

import com.furkan.mvvm_news_app.data.remote.NewsApi
import com.furkan.mvvm_news_app.data.remote.responses.NewsResponse
import com.furkan.mvvm_news_app.util.ApiResponseUtils.handleException
import com.furkan.mvvm_news_app.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NewsRepository @Inject constructor(
    private val api: NewsApi
) {
    suspend fun searchForNews(
        searchQuery: String,
        pageNumber: Int,
        fromDate: String,
        toDate: String
    ): Resource<NewsResponse> {

        return try {
            val response = api.getNews(
                searchQuery = searchQuery,
                pageNumber = pageNumber,
                fromDate = fromDate,
                toDate = toDate
            )
            Resource.Success(response)
        } catch (e: Throwable) {
            handleException(e)
        }

    }

}
