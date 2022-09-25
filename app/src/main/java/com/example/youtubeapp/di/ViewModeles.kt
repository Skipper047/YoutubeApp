package com.example.youtubeapp.di

import com.example.youtubeapp.ui.playlist.PlaylistViewModel
import com.example.youtubeapp.ui.playlist_detail.PlaylistDetailViewModel
import com.example.youtubeapp.ui.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { PlaylistViewModel(get(), get())}
    viewModel { PlaylistDetailViewModel(get())}
    viewModel { VideoViewModel()}
}