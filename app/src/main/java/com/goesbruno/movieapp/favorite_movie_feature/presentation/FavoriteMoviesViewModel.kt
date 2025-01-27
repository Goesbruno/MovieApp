package com.goesbruno.movieapp.favorite_movie_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.GetFavoriteMoviesUseCase
import com.goesbruno.movieapp.favorite_movie_feature.presentation.state.FavoriteMovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<FavoriteMovieUiState> =
        MutableStateFlow(FavoriteMovieUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            val movies = getFavoriteMoviesUseCase.invoke()
            _uiState.update { currentState ->
                currentState.copy(
                    movies = movies
                )
            }
        }
    }

}