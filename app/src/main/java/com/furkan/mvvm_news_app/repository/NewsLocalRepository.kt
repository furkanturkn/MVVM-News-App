package com.furkan.mvvm_news_app.repository

import com.furkan.mvvm_news_app.data.local.dao.ArticleDao
import com.furkan.mvvm_news_app.data.local.entity.ArticleEntity
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NewsLocalRepository @Inject constructor(
    private val db: ArticleDao
) {
    suspend fun insertOrUpdateArticle(note: ArticleEntity) = db.upsert(note)

    suspend fun getSavedArticles() = db.getAllArticles()
}