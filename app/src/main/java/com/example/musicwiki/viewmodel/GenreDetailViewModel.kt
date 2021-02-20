package com.example.musicwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.extensions.toLiveData
import com.example.musicwiki.models.GenreDetailModel
import com.example.musicwiki.models.GenreModel
import com.example.musicwiki.repositories.FmRepository
import com.example.musicwiki.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreDetailViewModel @Inject constructor(private val fmRepository: FmRepository) :
    ViewModel() {

    private val _genreDetailResult = MutableLiveData<Resource<GenreDetailModel>>()
    val genreDetailResultLiveData = _genreDetailResult.toLiveData()

    fun getGenreDetail(name: String) {
        viewModelScope.launch {
            fmRepository.getGenreDetail(name).catch { e ->
                _genreDetailResult.postValue(Resource.Error(e.localizedMessage))
            }.collect {
                _genreDetailResult.postValue(Resource.Success(it))
            }
        }
    }
}