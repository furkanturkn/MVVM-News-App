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
import com.furkan.mvvm_news_app.news_main.NewsMainScreen
import com.furkan.mvvm_news_app.ui.theme.MVVMNewsAppTheme
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
                        "news_detail_screen/{newsName}",
                        arguments = listOf(
                            navArgument("newsName") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val newsName = remember {
                            it.arguments?.getString("newsName")
                        }

                    }
                }
            }
        }
    }
}
