package com.furkan.mvvm_news_app.presentation.ui.news_main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkan.mvvm_news_app.data.remote.responses.Article
import com.furkan.mvvm_news_app.repository.NewsRepository
import com.furkan.mvvm_news_app.util.Constants.NEWS_API_PAGE_SIZE
import com.furkan.mvvm_news_app.util.Constants.NEWS_API_TOPIC
import com.furkan.mvvm_news_app.util.Resource
import com.furkan.mvvm_news_app.util.getCurrentDateTimeString
import com.furkan.mvvm_news_app.util.nDaysAfterFromDateString
import com.furkan.mvvm_news_app.util.nDaysBeforeFromDateString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsMainViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _isDatePickerDialogVisible = mutableStateOf(false)
    val isDatePickerDialogVisible: State<Boolean> = _isDatePickerDialogVisible

    private val _pickedDate = mutableStateOf(getCurrentDateTimeString().nDaysBeforeFromDateString(10))
    val pickedDate: State<String> = _pickedDate


    private var currentPage = 1
    var newsList = mutableStateOf<List<Article>>(listOf())
    var isLoading = mutableStateOf(false)
    var endReachOfPage = mutableStateOf(false)
    var loadError = mutableStateOf("")

    init {
        fetchNews(getCurrentDateTimeString().nDaysBeforeFromDateString(10))
    }

    fun onEvent(event: NewsMainEvent) {
        when (event) {
            is NewsMainEvent.ShowDatePickerDialog -> {
                _isDatePickerDialogVisible.value = true
            }
            is NewsMainEvent.DismissDatePickerDialog -> {
                _isDatePickerDialogVisible.value = false
            }
            is NewsMainEvent.FetchNews -> {
                fetchNews(event.fromDate)
            }
            is NewsMainEvent.UpdatePickedDate -> {
                _pickedDate.value = event.pickedDate
            }
        }
    }

    fun fetchNews(fromDate: String) = viewModelScope.launch {
        isLoading.value = true
        val result = repository.searchForNews(
            searchQuery = NEWS_API_TOPIC,
            pageNumber = currentPage,
            fromDate = fromDate,
            toDate = fromDate.nDaysAfterFromDateString(1)
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