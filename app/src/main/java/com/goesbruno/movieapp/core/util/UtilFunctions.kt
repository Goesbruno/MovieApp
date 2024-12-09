package com.goesbruno.movieapp.core.util

import timber.log.Timber

object UtilFunctions {

    fun logError(tag: String, message: String){
        Timber.tag(tag).log(priority = 1, message = "Error -> $message")
    }

    fun logInfo(tag: String, message: String){
        Timber.tag(tag).log(priority = 1, message = "Info -> $message")
    }
}