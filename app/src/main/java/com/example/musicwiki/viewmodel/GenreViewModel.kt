package com.example.musicwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.extensions.toLiveData
import com.example.musicwiki.models.GenreModel
import com.example.musicwiki.repositories.FmRepository
import com.example.musicwiki.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val fmRepository: FmRepository) :
    ViewModel() {

    private val _genreResult = MutableLiveData<Resource<GenreModel>>()
    val genreResultLiveData = _genreResult.toLiveData()

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            fmRepository.getGenres().catch { e ->
                _genreResult.postValue(Resource.Error(e.localizedMessage))
            }.collect {
                _genreResult.postValue(Resource.Success(it))
            }
        }
    }
}