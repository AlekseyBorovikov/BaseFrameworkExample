package com.example.data.repository

import com.example.module.SerialCharacter

interface CharacterRepository {

    suspend fun getCharacters(
        page: Int,
        name: String? = null
    ): Result<List<SerialCharacter>>

    suspend fun getCharacterDetails(
        characterId: Int
    ): Result<SerialCharacter>

}