package com.example.musicwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.extensions.toLiveData
import com.example.musicwiki.models.AlbumModel
import com.example.musicwiki.models.GenreDetailModel
import com.example.musicwiki.repositories.FmRepository
import com.example.musicwiki.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreAlbumViewModel @Inject constructor(private val fmRepository: FmRepository) :
    ViewModel() {

    private val _genreAlbumsResult = MutableLiveData<Resource<AlbumModel>>()
    val genreAlbumResultLiveData = _genreAlbumsResult.toLiveData()

    fun getGenreAlbums(name: String) {
        viewModelScope.launch {
            fmRepository.getGenreAlbums(name).catch { e ->
                _genreAlbumsResult.postValue(Resource.Error(e.localizedMessage))
            }.collect {
                _genreAlbumsResult.postValue(Resource.Success(it))
            }
        }
    }
}