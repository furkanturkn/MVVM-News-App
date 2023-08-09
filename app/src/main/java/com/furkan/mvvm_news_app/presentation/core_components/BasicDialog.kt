package com.furkan.mvvm_news_app.presentation.core_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.furkan.mvvm_news_app.R
import com.furkan.mvvm_news_app.presentation.theme.SpaceLarge
import com.furkan.mvvm_news_app.presentation.theme.SpaceMedium
import com.furkan.mvvm_news_app.presentation.theme.SpaceSmall

@Composable
fun BasicDialog(
    title: String = stringResource(id = R.string.info),
    onDismissRequest: () -> Unit? = { },
    onRightButtonClick: () -> Unit? = { },
    onLeftButtonClick: () -> Unit? = { },
    showLeftButton: Boolean = true,
    showRightButton: Boolean = true,
    showTitle: Boolean = true,
    dialogIcon: ImageVector? = null,
    leftButtonText: String? = stringResource(id = R.string.cancel),
    rightButtonText: String? = stringResource(id = R.string.yes),
    content: @Composable() () -> Unit?
) {
    Dialog(onDismissRequest = {
        onDismissRequest()
    }) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(SpaceMedium)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (dialogIcon != null) {
                    Icon(
                        modifier = Modifier.fillMaxSize(0.2F),
                        imageVector = dialogIcon,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = stringResource(id = R.string.info),

                        )
                }
                Spacer(modifier = Modifier.height(SpaceSmall))
                if (showTitle) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(SpaceLarge))
            BoxWithConstraints {
                Box(
                    Modifier
                        .heightIn(max = 350.dp)
                        .fillMaxWidth()
                ) {
                    content()
                }
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.align(Alignment.End)
            ) {
                if (showLeftButton) {
                    Text(
                        text = leftButtonText.toString(),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            onLeftButtonClick()
                        }
                    )
                }

                if (showRightButton) {
                    Spacer(modifier = Modifier.width(SpaceMedium))
                    Text(
                        text = rightButtonText.toString(),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            onRightButtonClick()
                        }
                    )
                }
            }
        }
    }
}