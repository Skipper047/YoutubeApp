package com.example.youtubeapp.di

import com.example.youtubeapp.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single { Repository(get()) }
}