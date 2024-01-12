package com.yatish.data.mapper

import com.yatish.data.TestData.characterModel
import com.yatish.data.TestData.characterModelDto
import org.junit.Assert
import org.junit.Test


class CharactersMapperTest {

    private val mapper = CharactersMapper()

    @Test
    fun `GIVEN Character dto as input WHEN mapper method called THEN return converted character domain model`() {
        val dtoModel = characterModelDto

        val mappedModel = mapper.map(dtoModel)

        Assert.assertEquals(mappedModel, characterModel)
    }

}