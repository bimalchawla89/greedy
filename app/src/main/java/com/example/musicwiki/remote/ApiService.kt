package com.example.musicwiki.remote

import com.example.musicwiki.models.*
import com.example.musicwiki.utils.Constants
import com.example.musicwiki.viewmodel.GenreViewModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?method=tag.getTopTags&format=json&api_key="+Constants.API_KEY)
    suspend fun getGenres(): GenreModel

    @GET("?method=tag.getinfo&format=json&api_key=" + Constants.API_KEY)
    suspend fun getGenreDetail(@Query("tag") tag:String): GenreDetailModel

    @GET("?method=tag.gettopalbums&format=json&api_key=" + Constants.API_KEY)
    suspend fun getGenreAlbums(@Query("tag") tag:String): AlbumModel

    @GET("?method=tag.gettopartists&format=json&api_key=" + Constants.API_KEY)
    suspend fun getGenreArtists(@Query("tag") tag:String): ArtistModel

    @GET("?method=tag.gettoptracks&format=json&api_key=" + Constants.API_KEY)
    suspend fun getGenreTracks(@Query("tag") tag:String): TrackModel

    @GET("?method=artist.getinfo&format=json&api_key=" + Constants.API_KEY)
    suspend fun getArtistInfo(@Query("artist") tag:String): ArtistDetailModel

    @GET("?method=album.getinfo&format=json&api_key=" + Constants.API_KEY)
    suspend fun getAlbumInfo(@Query("album") album:String, @Query("artist") artist:String): AlbumDetailModel
}
