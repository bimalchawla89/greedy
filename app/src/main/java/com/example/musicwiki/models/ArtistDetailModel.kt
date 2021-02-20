package com.example.musicwiki.models

import kotlinx.serialization.Serializable

@Serializable
data class ArtistDetailModel(
    val artist: ArtistInnerDetailModel
)

@Serializable
data class ArtistInnerDetailModel(
    val name: String,
    val mbid: String,
    val url: String,
    var image: List<Image>,
    var stats: Stat,
    val bio: Bio,
    val tags: TagList,
    val similar: Similar
)

@Serializable
data class Similar(
    val artist: List<ArtistInnerItem>
)

@Serializable
data class Bio(
    val summary: String,
    val content: String
)

@Serializable
data class Stat(
    val listeners: String,
    val playcount: String
)