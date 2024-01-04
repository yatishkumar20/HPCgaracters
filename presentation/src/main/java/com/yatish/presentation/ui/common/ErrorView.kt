package com.yatish.presentation.ui.common

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorView(message: String) {
    CustomText(
        text = message,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.error,
        modifier = Modifier
            .fillMaxHeight()
            .padding(4.dp),
        textAlign = TextAlign.Center
    )
}