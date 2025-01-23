package com.example.bookbank.util


sealed class NetworkResult<T>(val data:T? = null, val error: T? = null)
{
    class Idle<T> : NetworkResult<T>()
    class Loading<T> : NetworkResult<T>()
    class Success<T>(data: T? = null) : NetworkResult<T>(data = data)
    class Error<T>(errorMessage :T? = null) : NetworkResult<T>(error = errorMessage)
}