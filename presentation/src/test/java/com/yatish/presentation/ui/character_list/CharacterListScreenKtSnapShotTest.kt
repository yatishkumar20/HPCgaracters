package com.yatish.presentation.ui.character_list

import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import com.yatish.presentation.model.CharacterItemUIModel
import org.junit.Rule
import org.junit.Test

class CharacterListScreenKtSnapShotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = PIXEL_5
    )

    @Test
    fun character_list_item_composable() {
        paparazzi.snapshot {
            CharacterItemView(getCharacterItem(), onItemClick = {_, _ -> })
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
}