package com.furkan.mvvm_news_app.presentation.ui.article_detail.components

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CustomWebView(webViewUrl: String) {
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            loadUrl(webViewUrl)
        }
    }, update = {
        it.loadUrl(webViewUrl)
    })
}