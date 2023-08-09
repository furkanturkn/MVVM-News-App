package com.furkan.mvvm_news_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.furkan.mvvm_news_app.data.local.dao.ArticleDao
import com.furkan.mvvm_news_app.data.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}