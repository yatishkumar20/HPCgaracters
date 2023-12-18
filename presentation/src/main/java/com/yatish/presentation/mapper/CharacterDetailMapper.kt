package com.yatish.presentation.mapper

import com.yatish.domain.model.CharacterModel
import com.yatish.presentation.model.CharacterDetailsUIModel
import javax.inject.Inject

class CharacterDetailMapper @Inject constructor() {

    fun map(model: CharacterModel): CharacterDetailsUIModel =
        with(model) {
            CharacterDetailsUIModel(
                actor = actor,
                alive = alive,
                dateOfBirth = dateOfBirth,
                gender = gender,
                hairColour = hairColour,
                hogwartsStaff = hogwartsStaff,
                hogwartsStudent = hogwartsStudent,
                house = house,
                id = id,
                image = image,
                name = name
            )
        }
}