package com.example.musicwiki.models

import kotlinx.serialization.Serializable

@Serializable
data class TrackModel(
    val tracks: Track
)

@Serializable
data class Track(
    val track: List<TrackItem>
)

@Serializable
data class TrackItem(
    val name: String,
    val mbid: String,
    val url: String,
    val duration: String,
    val artist: TrackArtist,
    var image: List<Image>
)

@Serializable
data class TrackArtist(
    val name: String,
    val mbid: String,
    val url: String,
)