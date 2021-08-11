package com.sithuhein.mm.domain.resultState

sealed class ResultStatus<T>(
    val data : T? = null,
    val message : String? = null,
) {
    class Loading<T>() : ResultStatus<T>()
    class Success<T>(data: T) : ResultStatus<T>(data)
    class NetworkConnection<T>(message: String?) : ResultStatus<T>(message = message)
    class Error<T>(message: String?) : ResultStatus<T>(message = message)
}