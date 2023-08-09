package com.furkan.mvvm_news_app.data.remote.responses

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
    val code: String? = null,
    val message: String? = null
)