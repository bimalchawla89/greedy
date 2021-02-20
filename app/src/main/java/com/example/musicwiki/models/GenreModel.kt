package com.example.musicwiki.models

import kotlinx.serialization.Serializable

@Serializable
data class GenreModel(
    val toptags: TagList
)

@Serializable
data class TagList(
    val tag: List<Tag>
)

@Serializable
data class Tag(
    val name: String,
    val count: Long,
    val reach: Long,
    val url: String
)