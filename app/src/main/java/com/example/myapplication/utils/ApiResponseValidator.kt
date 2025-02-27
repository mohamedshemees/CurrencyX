package com.example.myapplication.utils


class ApiResponseValidator {
    fun validateResponse(responseError:Int): ResponseHandler<Unit, Error> {
        if (responseError==429) {
            return ResponseHandler.Failure(ResponseErrors.LIMIT_EXCEEDED)
        }
        if (responseError==500) {
            return ResponseHandler.Failure(ResponseErrors.CONNECTION_ERROR)
        }
        return ResponseHandler.Success(Unit)
    }
    enum class ResponseErrors: Error{
        LIMIT_EXCEEDED,
        CONNECTION_ERROR
    }
}