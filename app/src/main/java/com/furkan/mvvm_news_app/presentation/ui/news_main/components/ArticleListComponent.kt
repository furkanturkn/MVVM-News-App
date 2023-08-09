package com.furkan.mvvm_news_app.presentation.ui.news_main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.furkan.mvvm_news_app.presentation.ui.news_main.NewsMainViewModel

@Composable
fun ArticleList(
    navController: NavController,
    viewModel: NewsMainViewModel = hiltViewModel()
) {
    val articleList by remember { viewModel.newsList }
    val endReachOfPage by remember { viewModel.endReachOfPage }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inversePrimary)
    ) {
        items(articleList.size) { articleIndex ->
            ArticleItem(
                article = articleList[articleIndex],
                navController = navController,
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (isLoading && !endReachOfPage) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }

        if (!isLoading && !endReachOfPage) {
            item {
                LaunchedEffect(key1 = true) {
                    viewModel.fetchNews(viewModel.pickedDate.value)
                }
            }
        }
    }
}