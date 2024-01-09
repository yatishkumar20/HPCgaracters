package com.yatish.presentation.ui.character_details

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.yatish.presentation.model.CharacterDetailsUIModel
import org.junit.Rule
import org.junit.Test

class CharacterDetailsScreenSnapShotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Test
    fun character_list_item_composable() {
        paparazzi.snapshot {
            CharacterDetailsView(getCharacterDetails())
        }
    }

    private fun getCharacterDetails(): CharacterDetailsUIModel {
        return CharacterDetailsUIModel(
            actor = "Harry Potter",
            image = "",
            id = "1",
            house = "Gryffindor",
            dateOfBirth = "20/01/1990",
            name = "Daniel",
            hogwartsStaff = false,
            hogwartsStudent = true
        )
    }
}