package com.furkan.mvvm_news_app.data.remote.responses

import java.io.Serializable

data class Article(
    var id: Int? = null,
    val author: String? = "Unknown Author",
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?
) : Serializable