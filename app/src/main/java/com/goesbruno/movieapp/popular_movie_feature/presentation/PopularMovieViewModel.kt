package com.goesbruno.movieapp.popular_movie_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.goesbruno.movieapp.popular_movie_feature.domain.source.PopularMovieRemoteDataSource
import com.goesbruno.movieapp.popular_movie_feature.domain.usecase.GetPopularMoviesUseCase
import com.goesbruno.movieapp.popular_movie_feature.presentation.state.PopularMovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<PopularMovieUiState> =
        MutableStateFlow(PopularMovieUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        val movies = getPopularMoviesUseCase.invoke()
            .cachedIn(viewModelScope)
        _uiState.update { currentState ->
            currentState.copy(
                movies = movies
            )
        }

    }


}