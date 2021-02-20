package com.example.musicwiki.models

import kotlinx.serialization.Serializable

@Serializable
data class AlbumDetailModel(
    val album: AlbumDetailInnerModel
)

@Serializable
data class AlbumDetailInnerModel(
    val name: String,
    val url: String,
    val artist: String,
    val mbid: String,
    val image: List<Image>,
    val listeners: String,
    val playcount: String,
    val tracks: TrackModel,
    val wiki: Wiki,
    val tags: TagList
)