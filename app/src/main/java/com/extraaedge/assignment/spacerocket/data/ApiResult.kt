package com.extraaedge.assignment.spacerocket.data

sealed class ApiResult<T : Any> {
    class Success<T : Any>(val data: T) : ApiResult<T>()
    class Error<T : Any>(val message: String?) : ApiResult<T>()
}