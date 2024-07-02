package com.gamar.testproject.remote.utils

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Error(val error: Exception? = null): ResultWrapper<Nothing>()
}