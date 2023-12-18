package com.yatish.presentation.model

data class CharacterDetailsUIModel(
    val actor: String,
    val alive: Boolean,
    val dateOfBirth: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
)
