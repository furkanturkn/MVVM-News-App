package com.furkan.mvvm_news_app.presentation.ui.news_main

sealed class NewsMainEvent {
    object ShowDatePickerDialog : NewsMainEvent()
    object DismissDatePickerDialog : NewsMainEvent()
    data class FetchNews(val fromDate: String) : NewsMainEvent()
    data class UpdatePickedDate(val pickedDate: String) : NewsMainEvent()
}