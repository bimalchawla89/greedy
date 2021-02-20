package com.example.musicwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.extensions.toLiveData
import com.example.musicwiki.models.AlbumDetailModel
import com.example.musicwiki.models.AlbumModel
import com.example.musicwiki.repositories.FmRepository
import com.example.musicwiki.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(private val fmRepository: FmRepository) :
    ViewModel() {

    private val _albumDetailResult = MutableLiveData<Resource<AlbumDetailModel>>()
    val genreAlbumResultLiveData = _albumDetailResult.toLiveData()

    fun getAlbumDetail(name: String, artist: String) {
        viewModelScope.launch {
            fmRepository.getAlbumDetail(name, artist).catch { e ->
                _albumDetailResult.postValue(Resource.Error(e.localizedMessage))
            }.collect {
                _albumDetailResult.postValue(Resource.Success(it))
            }
        }
    }
}