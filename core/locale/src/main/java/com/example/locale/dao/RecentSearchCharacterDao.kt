package com.example.locale.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.locale.model.RecentSearchCharacterEntity

@Dao
interface RecentSearchCharacterDao {

    suspend fun insertRecentKeyword(entity: RecentSearchCharacterEntity)

    @Delete
    suspend fun deleteRecentKeyword(entity: RecentSearchCharacterEntity)

    @Update
    suspend fun updateRecentKeyword(entity: RecentSearchCharacterEntity)

    @Query("select * from recentSearchCharacter")
    suspend fun getAllRecentKeyword(): List<RecentSearchCharacterEntity>
}