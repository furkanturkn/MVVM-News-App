package com.furkan.mvvm_news_app.presentation.ui.favorite_articles

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkan.mvvm_news_app.data.local.entity.ArticleEntity
import com.furkan.mvvm_news_app.repository.NewsLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteArticlesViewModel @Inject constructor(
    private val localRepository: NewsLocalRepository
) : ViewModel() {
    var favoritesNewsList = mutableStateOf<List<ArticleEntity>>(listOf())

    init {
        fetchFavoritesNewsFromLocalDataSource()
    }

    private fun fetchFavoritesNewsFromLocalDataSource() {
        viewModelScope.launch {
            favoritesNewsList.value = localRepository.getSavedArticles()
        }
    }
}