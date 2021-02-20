package com.example.musicwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.extensions.toLiveData
import com.example.musicwiki.models.ArtistModel
import com.example.musicwiki.models.GenreDetailModel
import com.example.musicwiki.repositories.FmRepository
import com.example.musicwiki.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreArtistViewModel@Inject constructor(private val fmRepository: FmRepository) :
    ViewModel() {

    private val _genreArtistsResult = MutableLiveData<Resource<ArtistModel>>()
    val genreArtistsResultLiveData = _genreArtistsResult.toLiveData()

    fun getGenreArtists(name: String) {
        viewModelScope.launch {
            fmRepository.getGenreArtists(name).catch { e ->
                _genreArtistsResult.postValue(Resource.Error(e.localizedMessage))
            }.collect {
                _genreArtistsResult.postValue(Resource.Success(it))
            }
        }
    }
}