package com.furkan.mvvm_news_app.data.remote

import com.furkan.mvvm_news_app.data.remote.responses.NewsResponse
import com.furkan.mvvm_news_app.util.Constants.API_KEY
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
        pageSize: Int? = 6,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): NewsResponse

}