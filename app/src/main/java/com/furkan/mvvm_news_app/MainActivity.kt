package com.furkan.mvvm_news_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.furkan.mvvm_news_app.presentation.article_detail.ArticleDetailScreen
import com.furkan.mvvm_news_app.presentation.news_main.NewsMainScreen
import com.furkan.mvvm_news_app.presentation.theme.MVVMNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMNewsAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "news_main_screen"
                ) {
                    composable("news_main_screen") {
                        NewsMainScreen(navController = navController)
                    }
                    composable(
                        "article_detail_screen/{newsUrl}",
                        arguments = listOf(
                            navArgument("newsUrl") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val newsUrl = remember {
                            it.arguments?.getString("newsUrl")
                        }
                        ArticleDetailScreen(
                            articleUrl = newsUrl ?: "",
                            navController = navController
                        )

                    }
                }
            }
        }
    }
}
