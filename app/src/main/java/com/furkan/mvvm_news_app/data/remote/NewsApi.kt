package com.furkan.mvvm_news_app.data.remote

import com.furkan.mvvm_news_app.BuildConfig
import com.furkan.mvvm_news_app.data.remote.responses.NewsResponse
import com.furkan.mvvm_news_app.util.Constants.DEFAULT_NEWS_API_PAGE_NUMBER
import com.furkan.mvvm_news_app.util.Constants.NEWS_API_PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = DEFAULT_NEWS_API_PAGE_NUMBER,
        @Query("pageSize")
        pageSize: Int? = NEWS_API_PAGE_SIZE,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): NewsResponse

}