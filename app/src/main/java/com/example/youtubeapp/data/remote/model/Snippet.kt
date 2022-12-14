package com.example.youtubeapp.data.remote.model

data class Snippet(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val localized: Localized,
    val publishedAt: String,
    val thumbnails: Thumbnails,
    val title: String,
    var kind: String,
    var resourceId: ResourceId
)
data class ResourceId(
    var kind: String,
    var videoId: String
)