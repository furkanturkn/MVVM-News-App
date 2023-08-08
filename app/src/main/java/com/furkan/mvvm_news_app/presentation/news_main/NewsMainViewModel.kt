package com.furkan.mvvm_news_app.presentation.news_main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkan.mvvm_news_app.data.remote.responses.Article
import com.furkan.mvvm_news_app.repository.NewsRepository
import com.furkan.mvvm_news_app.util.Constants.NEWS_API_PAGE_SIZE
import com.furkan.mvvm_news_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsMainViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private var currentPage = 1

    var newsList = mutableStateOf<List<Article>>(listOf())

    var isLoading = mutableStateOf(false)
    var endReachOfPage = mutableStateOf(false)
    var loadError = mutableStateOf("")

    init {
        getBreakingNews()
    }

    fun getBreakingNews() = viewModelScope.launch {
        isLoading.value = true
        val result = repository.searchForNews(
            searchQuery = "football",
            pageNumber = currentPage
        )

        when (result) {
            is Resource.Success -> {
                endReachOfPage.value =
                    currentPage * NEWS_API_PAGE_SIZE >= result.data!!.totalResults
                val articles = result.data.articles.filterNot { entry ->
                    entry.urlToImage.isNullOrEmpty() ||
                            newsList.value.any { it.title == entry.title }
                }.mapIndexed { _, entry ->
                    Article(
                        id = entry.id,
                        author = entry.author,
                        content = entry.content,
                        description = entry.description,
                        publishedAt = entry.publishedAt,
                        source = entry.source,
                        title = entry.title,
                        url = entry.url,
                        urlToImage = entry.urlToImage
                    )
                }
                currentPage++

                loadError.value = ""
                isLoading.value = false
                newsList.value += articles
            }

            is Resource.Error -> {
                loadError.value = result.message!!
                isLoading.value = false
            }

            is Resource.Loading -> {}
        }
    }

}