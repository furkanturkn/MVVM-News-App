package com.furkan.mvvm_news_app.presentation.ui.news_main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.furkan.mvvm_news_app.R
import com.furkan.mvvm_news_app.presentation.core_components.StandardButton
import com.furkan.mvvm_news_app.presentation.ui.news_main.components.ArticleList
import com.furkan.mvvm_news_app.presentation.ui.news_main.components.CustomDatePickerDialog

@Composable
fun NewsMainScreen(
    navController: NavController,
    viewModel: NewsMainViewModel = hiltViewModel()
) {

    Surface(
        color = MaterialTheme.colorScheme.inversePrimary,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {

            if(viewModel.isDatePickerDialogVisible.value){
                CustomDatePickerDialog(
                    onDismissRequest = {
                        viewModel.onEvent(NewsMainEvent.DismissDatePickerDialog)
                    },
                    onDatePicked = {
                        println(it)
                        viewModel.onEvent(NewsMainEvent.UpdatePickedDate(it))
                        viewModel.onEvent(NewsMainEvent.FetchNews(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            StandardButton(
                onClick = {
                    viewModel.onEvent(NewsMainEvent.ShowDatePickerDialog)
                },
                resId = R.string.pick_date,
                textColor = Color.White
            )
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





