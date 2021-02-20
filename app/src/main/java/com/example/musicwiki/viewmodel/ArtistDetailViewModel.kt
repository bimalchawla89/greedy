package com.example.musicwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.extensions.toLiveData
import com.example.musicwiki.models.AlbumDetailModel
import com.example.musicwiki.models.ArtistDetailModel
import com.example.musicwiki.models.TrackModel
import com.example.musicwiki.repositories.FmRepository
import com.example.musicwiki.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(private val fmRepository: FmRepository) :
    ViewModel() {

    private val _artistDetailResult = MutableLiveData<Resource<ArtistDetailModel>>()
    val genreAlbumResultLiveData = _artistDetailResult.toLiveData()

    fun getArtistDetail(name: String) {
        viewModelScope.launch {
            fmRepository.getArtistDetail(name).catch { e ->
                _artistDetailResult.postValue(Resource.Error(e.localizedMessage))
            }.collect {
                _artistDetailResult.postValue(Resource.Success(it))
            }
        }
    }
}