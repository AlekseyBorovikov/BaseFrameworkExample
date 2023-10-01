package com.example.remote.model

import com.example.module.SerialCharacter

data class CharacterResponse(
    val info: Info,
    val results: ArrayList<SerialCharacter>,
)
