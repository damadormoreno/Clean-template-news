package com.deneb.newsapp.core.functional


sealed class Result<out T : Any>

class Success<out T : Any>(val data: T) : Result<T>()

class Error(
    val exception: Throwable,
    val message: String = exception.message ?: "An unknown error occurred!"
) : Result<Nothing>()