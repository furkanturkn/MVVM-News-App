package com.furkan.mvvm_news_app.presentation.ui.news_main

import com.furkan.mvvm_news_app.data.remote.responses.Article

sealed class NewsMainEvent {
    object ShowDatePickerDialog : NewsMainEvent()
    object DismissDatePickerDialog : NewsMainEvent()
    data class FetchNews(val fromDate: String) : NewsMainEvent()
    data class UpdatePickedDate(val pickedDate: String) : NewsMainEvent()
    data class SaveFavoriteToLocalDb(val article: Article) : NewsMainEvent()
}