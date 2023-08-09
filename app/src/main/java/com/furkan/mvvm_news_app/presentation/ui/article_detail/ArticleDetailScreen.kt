package com.furkan.mvvm_news_app.presentation.ui.article_detail

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.furkan.mvvm_news_app.presentation.ui.article_detail.components.ArticleDetailTopSection
import com.furkan.mvvm_news_app.presentation.ui.article_detail.components.CustomWebView

@Composable
fun ArticleDetailScreen(
    articleUrl: String,
    navController: NavController,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        ArticleDetailTopSection(
            navController,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) {
            CustomWebView(Uri.decode(articleUrl))
        }
    }


}



