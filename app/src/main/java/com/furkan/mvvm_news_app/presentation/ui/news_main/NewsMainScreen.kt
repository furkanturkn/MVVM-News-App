package com.furkan.mvvm_news_app.presentation.ui.news_main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.furkan.mvvm_news_app.R
import com.furkan.mvvm_news_app.presentation.core_components.StandardButton
import com.furkan.mvvm_news_app.presentation.theme.SpaceLarge
import com.furkan.mvvm_news_app.presentation.theme.SpaceSmall
import com.furkan.mvvm_news_app.presentation.ui.news_main.components.ArticleList
import com.furkan.mvvm_news_app.presentation.ui.news_main.components.CustomDatePickerDialog
import com.furkan.mvvm_news_app.util.Screen
import com.furkan.mvvm_news_app.util.UiText

@Composable
fun NewsMainScreen(
    navController: NavController,
    viewModel: NewsMainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Surface(
        color = MaterialTheme.colorScheme.inversePrimary,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {

            if (viewModel.isDatePickerDialogVisible.value) {
                CustomDatePickerDialog(
                    onDismissRequest = {
                        viewModel.onEvent(NewsMainEvent.DismissDatePickerDialog)
                    },
                    onDatePicked = {
                        viewModel.onEvent(NewsMainEvent.UpdatePickedDate(it))
                        viewModel.onEvent(NewsMainEvent.FetchNews(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(SpaceLarge))

            Image(
                painter = painterResource(id = R.drawable.worldnewsicon),
                contentDescription = "news icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(SpaceLarge))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StandardButton(
                    onClick = {
                        viewModel.onEvent(NewsMainEvent.ShowDatePickerDialog)
                    },
                    resId = R.string.pick_date,
                    textColor = Color.White
                )
                StandardButton(
                    onClick = {
                        navController.navigate(Screen.FavoriteArticlesScreen.route)
                    },
                    resId = R.string.my_favorites,
                    textColor = Color.White
                )
            }

            Spacer(modifier = Modifier.height(SpaceSmall))

            if (viewModel.loadError.value.asString(context) == UiText.errorNon()
                    .asString(context)
            ) {
                ArticleList(navController = navController)
            } else {
                Text(
                    text = viewModel.loadError.value.asString(context),
                    color = Color.DarkGray,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }

        }

    }
}





