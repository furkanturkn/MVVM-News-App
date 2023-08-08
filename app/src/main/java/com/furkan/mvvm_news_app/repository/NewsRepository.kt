package com.furkan.mvvm_news_app.repository

import com.furkan.mvvm_news_app.data.remote.NewsApi
import com.furkan.mvvm_news_app.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NewsRepository @Inject constructor(
    private val api: NewsApi
) {


}