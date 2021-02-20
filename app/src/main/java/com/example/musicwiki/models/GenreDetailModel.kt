package com.example.musicwiki.models

import kotlinx.serialization.Serializable

@Serializable
data class GenreDetailModel(
    val tag: DetailTag
)

@Serializable
data class DetailTag(
    val name: String,
    val wiki: Wiki,
    val total: Long,
    val reach: Long,
)

@Serializable
data class Wiki(
    val summary: String,
    val content: String,
    val published: String
)