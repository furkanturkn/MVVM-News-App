package com.furkan.mvvm_news_app.util

sealed class Screen(val route: String) {
    object NewsMainScreen : Screen("news_main_screen")
    object ArticleDetailScreen : Screen("article_detail_screen")
    object FavoriteArticlesScreen : Screen("favorite_article_screen")
}
