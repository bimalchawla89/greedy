package com.example.musicwiki.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistModel(
    @SerializedName("topartists") val topArtists: ArtistItem
)

@Serializable
data class ArtistItem(
    val artist: List<ArtistInnerItem>
)

@Serializable
data class ArtistInnerItem(
    val name: String,
    val mbid: String,
    val url: String,
    var image: List<Image>
)