package com.example.myapplication.utils

typealias RootError=Error

sealed class ResponseHandler<out D,out E: RootError> {
    data class Success<out D,out E:RootError>(val data: D) : ResponseHandler<D, E>()
    data class Failure<out D,out E:RootError>(val error: E) : ResponseHandler<D, E>()
}
