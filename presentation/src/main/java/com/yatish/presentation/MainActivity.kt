package com.yatish.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.yatish.presentation.theme.HPCharactersTheme
import com.yatish.presentation.ui.NavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HPCharactersTheme {
                NavigationGraph(navController = navController)
            }
        }
    }
}
