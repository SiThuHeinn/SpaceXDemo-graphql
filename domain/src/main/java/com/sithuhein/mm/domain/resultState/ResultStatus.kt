package com.sithuhein.mm.domain.resultState

sealed class ResultStatus<T>(
    val data : T? = null,
    val message : String? = null,
) {
    class Loading<T>() : ResultStatus<T>()
    class Success<T>(data: T) : ResultStatus<T>(data)
    class NetworkConnection<T>(data: T? = null, message: String?) : ResultStatus<T>(null, message)
    class Error<T>(message: String?) : ResultStatus<T>(message = message)
}