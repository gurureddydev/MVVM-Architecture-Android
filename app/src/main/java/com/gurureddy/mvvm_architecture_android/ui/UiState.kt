package com.gurureddy.mvvm_architecture_android.ui

sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>

    data class Error(val message: String) : UiState<Nothing>

    object Loading : UiState<Nothing>
}
