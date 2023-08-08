package com.furkan.mvvm_news_app.presentation.news_main

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import coil.size.Size
import com.furkan.mvvm_news_app.R
import com.furkan.mvvm_news_app.data.remote.responses.Article

@Composable
fun NewsMainScreen(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colorScheme.inversePrimary,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.worldnewsicon),
                contentDescription = "news icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ArticleList(navController = navController)
        }
    }
}

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
            ArticleComposable(
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
                    contentAlignment = Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }

        if (!isLoading && !endReachOfPage) {
            item {
                LaunchedEffect(key1 = true) {
                    viewModel.getBreakingNews()
                }
            }
        }
    }
}

@Composable
fun ArticleComposable(
    article: Article,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable {
                navController.navigate(
                    "article_detail_screen/${Uri.encode(article.url)}"
                )
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(MaterialTheme.colorScheme.inversePrimary, Color.White),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .clip(RoundedCornerShape(20.dp)) // Adjust the corner radius as needed
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

            Text(
                text = article.title,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(Color.Black.copy(alpha = 0.7f))

            )
        }
        Text(
            text = article.description.take(130) + "...",
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)

        )
    }
}

