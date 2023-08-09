package com.furkan.mvvm_news_app.data.local.mapper

import com.furkan.mvvm_news_app.data.local.entity.ArticleEntity
import com.furkan.mvvm_news_app.data.remote.responses.Article

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        id = id,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}