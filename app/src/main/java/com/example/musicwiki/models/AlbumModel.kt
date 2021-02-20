package com.example.musicwiki.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumModel(
    val albums: Album
)

@Serializable
data class Album(
    val album: List<AlbumItem>
)

@Serializable
data class AlbumItem(
    val name: String,
    val artist: Artist,
    val image: List<Image>
)

@Serializable
data class Artist(
    val name: String,
    val mbid: String,
    val url: String
)

@Serializable
data class Image(
    @SerializedName("#text") val text: String,
    val size: String
)