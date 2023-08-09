package com.furkan.mvvm_news_app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "articles"
)
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var articleId: Int? = null,
    val author: String? = "Unknown Author",
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String?
)