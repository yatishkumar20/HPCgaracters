package com.yatish.presentation.ui.common

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CircularProgressView(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(modifier = modifier)
}