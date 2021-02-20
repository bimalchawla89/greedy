package com.example.musicwiki.repositories

import com.example.musicwiki.models.*
import com.example.musicwiki.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FmRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getGenres(): Flow<GenreModel> {
        return flow {
            emit(apiService.getGenres())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGenreDetail(name: String): Flow<GenreDetailModel> {
        return flow {
            emit(apiService.getGenreDetail(name))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGenreAlbums(name: String): Flow<AlbumModel> {
        return flow {
            emit(apiService.getGenreAlbums(name))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGenreArtists(name: String): Flow<ArtistModel> {
        return flow {
            emit(apiService.getGenreArtists(name))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGenreTracks(name: String): Flow<TrackModel> {
        return flow {
            emit(apiService.getGenreTracks(name))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAlbumDetail(name: String, artist: String): Flow<AlbumDetailModel> {
        return flow {
            emit(apiService.getAlbumInfo(name, artist))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getArtistDetail(name: String): Flow<ArtistDetailModel> {
        return flow {
            emit(apiService.getArtistInfo(name))
        }.flowOn(Dispatchers.IO)
    }
}