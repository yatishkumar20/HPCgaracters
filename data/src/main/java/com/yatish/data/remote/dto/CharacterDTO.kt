package com.yatish.data.remote.dto

import kotlinx.serialization.SerialName

data class CharacterDTO(
    val actor: String,
    val alive: Boolean,
    @SerialName("alternate_actors")
    val alternateActors: List<String>,
    @SerialName("alternate_names")
    val alternateNames: List<String>,
    val ancestry: String,
    val dateOfBirth: String? = "",
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
    val patronus: String,
    val species: String,
    val wand: WandDTO,
    val wizard: Boolean,
    val yearOfBirth: Int
)