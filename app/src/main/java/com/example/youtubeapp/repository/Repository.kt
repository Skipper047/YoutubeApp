package com.example.youtubeapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapp.core.network.result.Resource
import com.example.youtubeapp.core.network.result.Resource.Companion.loading
import com.example.youtubeapp.data.remote.RemoteDataSource
import com.example.youtubeapp.data.remote.model.PlaylistItems
import com.example.youtubeapp.data.remote.model.Playlists
import kotlinx.coroutines.Dispatchers

class Repository(private val dataSource: RemoteDataSource) {

    fun getPlaylists(): LiveData<Resource<Playlists>> {
        return liveData(Dispatchers.IO) {
            emit(loading())
            val response = dataSource.getPlaylists()
            emit(response)
        }
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItems>> {
        return liveData(Dispatchers.IO) {
            emit(loading())
            val response = dataSource.getPlaylistItems(playlistId)
            emit(response)
        }
    }
}