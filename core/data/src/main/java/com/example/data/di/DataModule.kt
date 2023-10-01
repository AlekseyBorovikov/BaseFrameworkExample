package com.example.data.di

import com.example.data.repository.CharacterRepository
import com.example.data.repository.CharacterRepositoryImpl
import com.example.data.util.ConnectivityManagerNetworkMonitor
import com.example.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsCharacterRepository(
        topicsRepository: CharacterRepositoryImpl,
    ): CharacterRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}