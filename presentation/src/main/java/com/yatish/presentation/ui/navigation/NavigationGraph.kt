package com.yatish.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yatish.presentation.R
import com.yatish.presentation.constant.Constant.PARAM_CHARACTER_ID
import com.yatish.presentation.constant.Constant.PARAM_CHARACTER_NAME
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
                        navController.navigate("${HPCharactersScreen.CharacterDetailsScreen.route}/${id}/${name}")
                    })
                }
            )
        }
        composable(
            route = "${HPCharactersScreen.CharacterDetailsScreen.route}/{$PARAM_CHARACTER_ID}/{$PARAM_CHARACTER_NAME}",
            arguments = listOf(
                navArgument(PARAM_CHARACTER_ID) {
                    type = NavType.StringType
                },
                navArgument(PARAM_CHARACTER_NAME) {
                    type = NavType.StringType
                }
            )
        ) { backStack ->
            val characterName = backStack.arguments?.getString(PARAM_CHARACTER_NAME) ?: ""
            backStack.arguments?.getString(PARAM_CHARACTER_ID)?.let {
                AppBar(
                    title = characterName,
                    showBack = true,
                    content = {
                        CharacterDetailsScreen(it)
                    }
                ) {
                    navController.popBackStack()
                }
            }
        }
    }
}