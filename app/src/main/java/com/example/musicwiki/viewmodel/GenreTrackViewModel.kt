package com.example.musicwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.extensions.toLiveData
import com.example.musicwiki.models.TrackModel
import com.example.musicwiki.repositories.FmRepository
import com.example.musicwiki.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreTrackViewModel @Inject constructor(private val fmRepository: FmRepository) :
    ViewModel() {

    private val _genreTracksResult = MutableLiveData<Resource<TrackModel>>()
    val genreTracksResultLiveData = _genreTracksResult.toLiveData()

    fun getGenreTracks(name: String) {
        viewModelScope.launch {
            fmRepository.getGenreTracks(name).catch { e ->
                _genreTracksResult.postValue(Resource.Error(e.localizedMessage))
            }.collect {
                _genreTracksResult.postValue(Resource.Success(it))
            }
        }
    }
}