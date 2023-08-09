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
import com.furkan.mvvm_news_app.presentation.ui.article_detail.ArticleDetailScreen
import com.furkan.mvvm_news_app.presentation.ui.news_main.NewsMainScreen
import com.furkan.mvvm_news_app.presentation.theme.MVVMNewsAppTheme
import com.furkan.mvvm_news_app.util.Constants.NAV_ARG_ARTICLE_URL
import com.furkan.mvvm_news_app.util.Constants.NOT_FOUND_URL
import com.furkan.mvvm_news_app.util.Screen
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
                    startDestination = Screen.NewsMainScreen.route
                ) {
                    composable(Screen.NewsMainScreen.route) {
                        NewsMainScreen(navController = navController)
                    }
                    composable(
                        "${Screen.ArticleDetailScreen.route}/{$NAV_ARG_ARTICLE_URL}",
                        arguments = listOf(
                            navArgument(NAV_ARG_ARTICLE_URL) {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val newsUrl = remember {
                            it.arguments?.getString(NAV_ARG_ARTICLE_URL)
                        }
                        ArticleDetailScreen(
                            articleUrl = newsUrl ?: NOT_FOUND_URL,
                            navController = navController
                        )

                    }
                }
            }
        }
    }
}
