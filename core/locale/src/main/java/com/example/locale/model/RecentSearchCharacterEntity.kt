package com.example.locale.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recentSearchCharacter")
data class RecentSearchCharacterEntity (
    @PrimaryKey
    val query: String,
)