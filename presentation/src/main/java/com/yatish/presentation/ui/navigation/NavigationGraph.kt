package com.yatish.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yatish.presentation.R
import com.yatish.presentation.ui.character_details.CharacterDetailsScreen
import com.yatish.presentation.ui.character_list.CharacterListScreen
import com.yatish.presentation.ui.components.AppBar

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HPCharactersScreen.CharacterListScreen.route
    ) {
        composable(route = HPCharactersScreen.CharacterListScreen.route) {
            AppBar(
                title = stringResource(id = R.string.list_screen_name),
                content = {
                    CharacterListScreen(onCharacterItemClick = { id, name ->
                        navController.popBackStack()
                    })
                }
            )
        }
        composable(route = HPCharactersScreen.CharacterDetailsScreen.route + "/{${"id"}}" + "/${"name"}") {
            AppBar(
                title = stringResource(id = R.string.list_screen_name),
                showBack = true,
                content = {
                    CharacterDetailsScreen()
                }
            ) {
                navController.popBackStack()
            }
        }
    }
}