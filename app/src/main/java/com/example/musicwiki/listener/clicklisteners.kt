package com.example.musicwiki.listener

interface GenreClickListener {
    fun genreClicked(name:String)
}

interface AlbumCLickListener {
    fun albumClicked(name: String, artist: String)
}

interface ArtistClickListener {
    fun artistClicked(name: String)
}

interface TrackClickListener {
    fun trackClicked(name: String)
}