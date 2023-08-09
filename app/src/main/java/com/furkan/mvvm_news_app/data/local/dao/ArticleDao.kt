package com.furkan.mvvm_news_app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.furkan.mvvm_news_app.data.local.entity.ArticleEntity

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(articleEntity: ArticleEntity): Long

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<ArticleEntity>

}