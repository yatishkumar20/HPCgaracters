package com.yatish.presentation.ui.navigation

sealed class HPCharactersScreen(val route: String) {

    object CharacterListScreen: HPCharactersScreen(CHARACTER_LIST_SCREEN_ROUTE)
    object CharacterDetailsScreen: HPCharactersScreen(CHARACTER_DETAILS_SCREEN_ROUTE)

    companion object {
        const val CHARACTER_LIST_SCREEN_ROUTE = "hp_characters_list_screen"
        const val CHARACTER_DETAILS_SCREEN_ROUTE = "hp_characters_detail_screen"
    }
}