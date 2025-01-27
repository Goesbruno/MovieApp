package com.goesbruno.movieapp.movie_detail_feature.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.util.Constants
import com.goesbruno.movieapp.core.util.ResultData
import com.goesbruno.movieapp.core.util.UtilFunctions
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.AddFavoriteMovieUseCase
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.DeleteFavoriteMovieUseCase
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.IsFavoriteMovieUseCase
import com.goesbruno.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import com.goesbruno.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase.*
import com.goesbruno.movieapp.movie_detail_feature.presentation.state.MovieDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val isFavoriteMovieUseCase: IsFavoriteMovieUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(MovieDetailUiState())
    val uiState get() = _uiState.asStateFlow()

    private val movieId = savedStateHandle.get<Int>(key = Constants.MOVIE_DETAIL_ARGUMENT_KEY)

    init {
        movieId?.let { safeMovieId ->
            checkedFavorite(MovieDetailsEvent.CheckedFavorite(safeMovieId))
//            getMovieDetail(MovieDetailsEvent.GetMovieDetail(safeMovieId))


        }
    }

    private fun checkedFavorite(checkedFavorite: MovieDetailsEvent.CheckedFavorite) {
        event(checkedFavorite)
    }

    private fun getMovieDetail(getMovieDetail: MovieDetailsEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    fun onAddFavorite(movie: Movie) {
        if (_uiState.value.iconColor == Color.White) {
            event(MovieDetailsEvent.AddFavorite(movie = movie))
        } else {
            event(MovieDetailsEvent.RemoveFavorite(movie = movie))
        }
    }

    private fun event(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    getMovieDetailsUseCase.invoke(
                        params = Params(
                            movieId = event.movieId
                        )
                    ).collect { resultData ->
                        when (resultData) {
                            is ResultData.Success -> {
                                _uiState.update { currentState ->
                                    currentState.copy(
                                        isLoading = false,
                                        movieDetails = resultData.data?.second,
                                        results = resultData.data?.first ?: emptyFlow()
                                    )
                                }
                            }

                            is ResultData.Failure -> {
                                _uiState.update { currentState ->
                                    currentState.copy(
                                        isLoading = false,
                                        error = resultData.e?.message.toString()
                                    )
                                }
                                UtilFunctions.logError(
                                    tag = "DETAIL_ERROR:",
                                    message = resultData.e?.message.toString()
                                )
                            }

                            is ResultData.Loading -> {
                                _uiState.update { currentState ->
                                    currentState.copy(
                                        isLoading = true
                                    )
                                }
                            }
                        }
                    }
                }
            }

            is MovieDetailsEvent.AddFavorite -> {
                viewModelScope.launch {
                    addFavoriteMovieUseCase.invoke(
                        params = AddFavoriteMovieUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                _uiState.update { currentState ->
                                    currentState.copy(iconColor = Color.Red)
                                }
                            }

                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Error registering movie")
                            }

                            is ResultData.Loading -> {}
                        }
                    }
                }
            }

            is MovieDetailsEvent.CheckedFavorite -> {
                viewModelScope.launch {
                    isFavoriteMovieUseCase.invoke(
                        params = IsFavoriteMovieUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                _uiState.update { currentState ->
                                    if (result.data == true) {
                                        currentState.copy(iconColor = Color.Red)
                                    } else {
                                        currentState.copy(iconColor = Color.White)
                                    }
                                }
                            }

                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "An error occurred")
                            }

                            is ResultData.Loading -> TODO()
                        }
                    }
                }
            }

            is MovieDetailsEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    deleteFavoriteMovieUseCase.invoke(
                        params = DeleteFavoriteMovieUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                _uiState.update { currentState ->
                                    currentState.copy(iconColor = Color.White)
                                }
                            }

                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Error on deleting")
                            }

                            is ResultData.Loading -> {}
                        }
                    }
                }
            }
        }
    }
}