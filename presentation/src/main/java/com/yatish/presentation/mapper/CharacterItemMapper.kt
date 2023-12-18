package com.yatish.presentation.mapper

import com.yatish.domain.model.CharacterModel
import com.yatish.presentation.model.CharacterItemUIModel
import javax.inject.Inject

class CharacterItemMapper @Inject constructor() {

    fun map(model: CharacterModel): CharacterItemUIModel =
        with(model) {
            CharacterItemUIModel(
                name = name,
                house = house
            )
        }
}