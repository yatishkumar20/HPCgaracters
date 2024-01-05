package com.yatish.presentation

import com.yatish.domain.model.CharacterModel
import com.yatish.presentation.model.CharacterDetailsUIModel
import com.yatish.presentation.model.CharacterItemUIModel

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

    val characterUIModel = CharacterItemUIModel(
        id = "1",
        name = "Harry Potter",
        house = "Gryffindor",
        image = "https://ik.imagekit.io/hpapi/harry.jpg",
    )

    val characterDetailsUIModel = CharacterDetailsUIModel(
        id = "1",
        name = "Harry Potter",
        house = "Gryffindor",
        dateOfBirth = "31-07-1980",
        actor = "Daniel Radcliffe",
        alive = true,
        gender = "male",
        hairColour = "black",
        hogwartsStudent = true,
        hogwartsStaff = false,
        image = "https://ik.imagekit.io/hpapi/harry.jpg",
    )

}