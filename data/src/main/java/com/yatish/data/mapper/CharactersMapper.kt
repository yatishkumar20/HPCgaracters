package com.yatish.data.mapper

import com.yatish.data.remote.dto.CharacterDTO
import com.yatish.domain.model.CharacterModel
import javax.inject.Inject

class CharactersMapper @Inject constructor() {
    fun map(model: CharacterDTO): CharacterModel =
        with(model) {
            CharacterModel(
                actor = actor,
                alive = alive,
                dateOfBirth = dateOfBirth ?: "",
                gender = gender,
                hairColour = hairColour,
                hogwartsStaff = hogwartsStaff,
                hogwartsStudent = hogwartsStudent,
                house = house,
                id = id,
                image = image,
                name = name,
            )
        }
}