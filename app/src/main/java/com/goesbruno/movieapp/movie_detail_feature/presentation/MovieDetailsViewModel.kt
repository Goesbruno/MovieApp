package com.goesbruno.movieapp.movie_detail_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goesbruno.movieapp.core.util.ResultData
import com.goesbruno.movieapp.core.util.UtilFunctions
import com.goesbruno.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import com.goesbruno.movieapp.movie_detail_feature.presentation.state.MovieDetailUiState
import com.goesbruno.movieapp.movie_search_feature.domain.usecases.GetMovieSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(MovieDetailUiState())
    val uiState get() = _uiState.asStateFlow()

    fun getMovieDetail(getMovieDetail: MovieDetailsEvent.GetMovieDetail) {
        event(getMovieDetail)
    }
    private fun event(event: MovieDetailsEvent) {
        when(event) {
            is MovieDetailsEvent.GetMovieDetail -> {
                 viewModelScope.launch{
                     getMovieDetailsUseCase.invoke(
                         params = GetMovieDetailsUseCase.Params(
                             movieId= event.movieId
                         )
                     ).collect { resultData ->
                         when(resultData) {
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
        }
    }
}