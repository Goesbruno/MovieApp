package com.goesbruno.movieapp.core.util

import com.goesbruno.movieapp.BuildConfig

fun String?.toPosterUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"

fun String?.toBackdropUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"