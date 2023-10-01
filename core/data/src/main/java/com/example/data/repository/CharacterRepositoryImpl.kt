package com.example.data.repository

import com.example.module.SerialCharacter
import com.example.remote.api.CharacterService
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService
): CharacterRepository {

    override suspend fun getCharacters(page: Int, name: String?): Result<List<SerialCharacter>> {
        val response = characterService.getCharacters()
        if (response.isSuccessful && response.body() != null) {
            val characterResponse = response.body()
            return Result.success(characterResponse!!.results)
        }
        return Result.failure(Exception(response.message()))
    }

    override suspend fun getCharacterDetails(characterId: Int): Result<SerialCharacter> {
        val response = characterService.getCharacterDetails(characterId)
        if (response.isSuccessful && response.body() != null) {
            val result = response.body()
            return Result.success(result!!)
        }
        return Result.failure(Exception(response.message()))
    }

}