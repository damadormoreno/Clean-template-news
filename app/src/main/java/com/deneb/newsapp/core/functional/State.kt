package com.deneb.newsapp.core.functional

import com.deneb.newsapp.core.exception.Failure

sealed class State<T> {
    class Loading<T> : State<T>()
    data class Success<T>(val data: T) : State<T>()
    data class Failed<T>(val failure: Failure) : State<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(failure: Failure) = Failed<T>(failure)
    }
}