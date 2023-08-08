package com.furkan.mvvm_news_app.data.remote

import com.furkan.mvvm_news_app.BuildConfig
import com.furkan.mvvm_news_app.data.remote.responses.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("pageSize")
        pageSize: Int? = 4,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): NewsResponse

}