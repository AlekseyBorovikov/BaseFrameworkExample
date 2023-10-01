package com.example.locale.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.locale.model.RecentSearchCharacterEntity

@Dao
interface RecentSearchCharacterDao {

    @Insert
    fun insertRecentKeyword(entity: RecentSearchCharacterEntity)

    @Delete
    fun deleteRecentKeyword(entity: RecentSearchCharacterEntity)

    @Update
    fun updateRecentKeyword(entity: RecentSearchCharacterEntity)

    @Query("SELECT * FROM recentSearchCharacter")
    fun getAllRecentKeyword(): List<RecentSearchCharacterEntity>
}