package com.furkan.mvvm_news_app.presentation.ui.favorite_articles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.furkan.mvvm_news_app.data.local.entity.ArticleEntity

@Composable
fun FavoriteArticlesScreen(
    viewModel: FavoriteArticlesViewModel = hiltViewModel()
) {

    LazyColumn {
        items(viewModel.favoritesNewsList.value.size) { articleIndex ->
            ArticleFavoritesItem(viewModel.favoritesNewsList.value[articleIndex])
            Divider()
        }
    }
}

@Composable
fun ArticleFavoritesItem(article: ArticleEntity) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.inversePrimary)
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = article.title,
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.scale(0.5f)
                    )
                },
                success = {
                    SubcomposeAsyncImageContent()
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.title, style = MaterialTheme.typography.bodyLarge)
    }
}