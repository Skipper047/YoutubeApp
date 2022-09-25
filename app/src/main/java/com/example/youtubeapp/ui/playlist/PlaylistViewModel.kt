package com.example.youtubeapp.ui.playlist

import androidx.lifecycle.LiveData
import com.example.youtubeapp.core.ui.BaseViewModel
import com.example.youtubeapp.data.remote.model.Playlists
import com.example.youtubeapp.core.network.result.Resource
import com.example.youtubeapp.data.local.AppPrefs
import com.example.youtubeapp.repository.Repository


class PlaylistViewModel(private val repository: Repository, private val prefs: AppPrefs): BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<Playlists>> {
        return repository.getPlaylists()
    }

    fun setOnBoard(onBoard: Boolean) {
        prefs.onBoard = onBoard
    }
}