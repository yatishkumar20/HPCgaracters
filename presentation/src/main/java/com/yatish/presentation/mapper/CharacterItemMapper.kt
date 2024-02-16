package com.yatish.presentation.mapper

import com.yatish.domain.model.CharacterModel
import com.yatish.presentation.model.CharacterItemUIModel
import javax.inject.Inject

class CharacterItemMapper @Inject constructor() {

    fun map(model: List<CharacterModel>): List<CharacterItemUIModel> =
        model.map { domainModel ->
            with(domainModel) {
                CharacterItemUIModel(
                    name = name,
                    house = house,
                    id = id,
                    image = image
                )
            }
        }
}