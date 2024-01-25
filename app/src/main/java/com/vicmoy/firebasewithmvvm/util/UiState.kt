package com.vicmoy.firebasewithmvvm.util

sealed class UiState<out T> {
    // loading
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Failure(val error: String?) : UiState<Nothing>()
}