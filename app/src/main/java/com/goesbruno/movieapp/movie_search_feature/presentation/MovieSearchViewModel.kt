package com.goesbruno.movieapp.movie_search_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.goesbruno.movieapp.movie_search_feature.domain.usecases.GetMovieSearchUseCase
import com.goesbruno.movieapp.movie_search_feature.presentation.state.MovieSearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMovieSearchUseCases: GetMovieSearchUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<MovieSearchUiState> =
        MutableStateFlow(MovieSearchUiState())
    val uiState get() = _uiState.asStateFlow()

    fun fetch(query: String = "") {
        val movies = getMovieSearchUseCases.invoke(
            params = GetMovieSearchUseCase.Params(
                query = query,
                pagingConfig = pagingConfig()
            )
        ).cachedIn(viewModelScope)
        _uiState.update { currentState ->
            currentState.copy(movies = movies)
        }
    }

    fun event(event: MovieSearchEvent){
        _uiState.update { currentState ->
            when (event) {
                is MovieSearchEvent.EnteredQuery -> {
                    currentState.copy(query = event.value)
                }
            }
        }
    }

    private fun pagingConfig() = PagingConfig(
        pageSize = 20,
        initialLoadSize = 20
    )


}