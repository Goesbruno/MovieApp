package com.goesbruno.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.goesbruno.movieapp.core.presentation.MainScreen
import com.goesbruno.movieapp.popular_movie_feature.presentation.PopularMovieViewModel
import com.goesbruno.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                    MainScreen(navController = rememberNavController())
            }
        }
    }
}

