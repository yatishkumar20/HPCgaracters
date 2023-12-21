package com.yatish.data

import com.yatish.data.remote.dto.CharacterDTO
import com.yatish.data.remote.dto.WandDTO
import com.yatish.domain.model.CharacterModel

object TestData {
    val characterModel = CharacterModel(
        id = "1",
        name = "Harry Potter",
        dateOfBirth = "31-07-1980",
        actor = "Daniel Radcliffe",
        alive = true,
        gender = "male",
        house = "Gryffindor",
        hairColour = "black",
        hogwartsStudent = true,
        hogwartsStaff = false,
        image = "https://ik.imagekit.io/hpapi/harry.jpg",
    )

    val charactersList = listOf(characterModel)

    private val characterModelDto = CharacterDTO(
        id = "1",
        name = "Harry Potter",
        dateOfBirth = "31-07-1980",
        actor = "Daniel Radcliffe",
        alive = true,
        gender = "male",
        house = "Gryffindor",
        hairColour = "black",
        hogwartsStudent = true,
        hogwartsStaff = false,
        image = "https://ik.imagekit.io/hpapi/harry.jpg",
        alternateActors = emptyList(),
        alternateNames = emptyList(),
        ancestry = "half-blood",
        species = "human",
        yearOfBirth = 1980,
        wizard = true,
        eyeColour = "green",
        wand = WandDTO(
            wood = "holly",
            core = "phoenix tail feather",
            length = 11.0
        ),
        patronus = "stag",
    )

    val characterListDto = listOf(characterModelDto)
}