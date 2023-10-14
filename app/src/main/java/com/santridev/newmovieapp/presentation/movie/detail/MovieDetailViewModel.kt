package com.santridev.newmovieapp.presentation.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santridev.newmovieapp.base.dispatcher.DispatcherProvider
import com.santridev.newmovieapp.domain.resource.MovieDetailResource
import com.santridev.newmovieapp.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: GetMovieDetailUseCase, private val dispatcher: DispatcherProvider
) : ViewModel() {
    private val _movieDetail = MutableLiveData<MovieDetailResource>()
    val movieDetail: LiveData<MovieDetailResource> get() = _movieDetail

    fun getMovieDetail(movieId: Int) {
        _movieDetail.value = MovieDetailResource.Loading
        viewModelScope.launch(dispatcher.io) {
            _movieDetail.postValue(useCase.invoke(movieId))
        }
    }
}