package com.example.youtubeapp.di

import com.example.youtubeapp.core.network.networkModule
import com.example.youtubeapp.data.local.prefModule
import com.example.youtubeapp.data.remote.remoteDataSource

val koinModules = listOf(
    repoModules,
    viewModules,
    networkModule,
    remoteDataSource,
    prefModule
)