package com.yatish.presentation.ui.character_list

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.yatish.presentation.model.CharacterItemUIModel
import org.junit.Rule
import org.junit.Test

class CharacterListScreenKtTest {

    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun test_characters_list_screen() {
        testRule.setContent {
            CharacterItemView(item = getCharacterItem(), onItemClick = { _, _ -> })
        }
        testRule.run {
            onNodeWithText(NAME).assertExists()
            onNodeWithText(HOUSE).assertExists()
        }
    }

    private fun getCharacterItem(): CharacterItemUIModel {
        return CharacterItemUIModel(
            "Harry Potter",
            image = "",
            id = "1",
            house = "Gryffindor"
        )
    }

    private companion object {
        const val NAME = "Harry Potter"
        const val HOUSE = "Gryffindor"
    }
}