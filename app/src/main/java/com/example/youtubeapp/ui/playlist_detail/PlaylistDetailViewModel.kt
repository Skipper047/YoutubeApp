package com.example.youtubeapp.ui.playlist_detail

import androidx.lifecycle.LiveData
import com.example.youtubeapp.core.network.result.Resource
import com.example.youtubeapp.core.ui.BaseViewModel
import com.example.youtubeapp.data.remote.model.PlaylistItems
import com.example.youtubeapp.repository.Repository

class PlaylistDetailViewModel(private val repository: Repository): BaseViewModel() {
    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItems>> {
        return repository.getPlaylistItems(playlistId)
    }
}