package com.example.remote.api

import com.example.common.framework.annotations.Cacheable
import com.example.module.SerialCharacter
import com.example.remote.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @Cacheable(hours = 2)
    @GET(GET_CHARACTER_LIST)
    suspend fun getCharacters(
        @Query("page") page: Int = 0,
        @Query("name") name: String? = null
    ): Response<CharacterResponse>

    @Cacheable(hours = 2)
    @GET(GET_CHARACTER_DETAILS_BY_ID)
    suspend fun getCharacterDetails(
        @Path("character_id") characterId: Int
    ): Response<SerialCharacter>

    companion object {
        const val GET_CHARACTER_LIST = "character"
        const val GET_CHARACTER_DETAILS_BY_ID = "character/{character_id}"
    }
}