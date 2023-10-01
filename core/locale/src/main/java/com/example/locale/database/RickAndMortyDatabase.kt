package com.example.locale.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.locale.dao.RecentSearchCharacterDao
import com.example.locale.model.RecentSearchCharacterEntity

@Database(
    entities = [RecentSearchCharacterEntity::class],
    version = 1,
)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun recentSearchCharacterDao(): RecentSearchCharacterDao

    companion object {
        @Volatile
        private var instance: RickAndMortyDatabase? = null

        fun getInstance(context: Context, dbName: String): RickAndMortyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context, dbName).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context, dbName: String): RickAndMortyDatabase {
            return Room.databaseBuilder(context, RickAndMortyDatabase::class.java, dbName)
                .allowMainThreadQueries()
                .enableMultiInstanceInvalidation()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}