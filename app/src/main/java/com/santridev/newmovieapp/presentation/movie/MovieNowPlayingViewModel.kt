package com.santridev.newmovieapp.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santridev.newmovieapp.base.dispatcher.DispatcherProvider
import com.santridev.newmovieapp.domain.model.MovieUiModel
import com.santridev.newmovieapp.domain.resource.MovieResource
import com.santridev.newmovieapp.domain.usecase.GetMovieNowPlayingUseCase
import com.santridev.newmovieapp.domain.usecase.GetMoviePopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieNowPlayingViewModel @Inject constructor(
    private val getMovieNowPlayingUseCase: GetMovieNowPlayingUseCase,
    private val getMoviePopular: GetMoviePopularUseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _movieNowPlaying = MutableLiveData<List<MovieUiModel>>()
    val movieNowPlaying: LiveData<List<MovieUiModel>> get() = _movieNowPlaying

    private val _moviePopular = MutableLiveData<MovieResource>()
    val moviePopular: LiveData<MovieResource> get() = _moviePopular

    fun getMovieNowPlaying(page: Int) = viewModelScope.launch {
        getMovieNowPlayingUseCase.invoke(page).collect { resource ->
            when (resource) {
                MovieResource.Loading -> {}
                is MovieResource.Success -> {
                    _movieNowPlaying.value = resource.data
                }

                is MovieResource.Error -> Unit
            }
        }
    }

    fun getMoviePopular(page: Int) {
        _moviePopular.value = MovieResource.Loading
        viewModelScope.launch(dispatcher.io) {
            _moviePopular.postValue(getMoviePopular.invoke(page))
        }
    }
}