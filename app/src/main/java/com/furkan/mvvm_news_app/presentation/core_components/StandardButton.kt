package com.furkan.mvvm_news_app.presentation.core_components

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.furkan.mvvm_news_app.R

@Composable
fun StandardButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes resId: Int? = null,
    text: String? = null,
    textColor: Color = Color.White,
    buttonColor: ButtonColors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
    enabled: Boolean = true,
    content: @Composable() (() -> Unit)? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = buttonColor
    ) {

        content?.let {
            it()
        }

        resId?.let {
            Text(
                text = stringResource(id = resId),
                color = textColor
            )
        }
        text?.let {
            Text(
                text = text,
                color = textColor
            )
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    StandardButton(
        onClick = {},
        resId = R.string.app_name,
    )
}
