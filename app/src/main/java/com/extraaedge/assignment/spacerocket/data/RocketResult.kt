package com.extraaedge.assignment.spacerocket.data

sealed class RocketResult<T : Any> {
    class InProgress<T : Any> : RocketResult<T>()
    class Success<T : Any>(val data: T) : RocketResult<T>()
    class Error<T : Any>(val message: String?) : RocketResult<T>()
}