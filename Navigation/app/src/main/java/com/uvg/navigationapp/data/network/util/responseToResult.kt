package com.uvg.navigationapp.data.network.util

import com.uvg.navigationapp.domain.network.util.DataError
import io.ktor.client.statement.HttpResponse
import com.uvg.navigationapp.domain.network.util.Result
import io.ktor.client.call.body

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, DataError> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch(e: Exception) {
                Result.Error(DataError.GENERIC_ERROR)
            }
        }

        else -> Result.Error(DataError.GENERIC_ERROR)
    }
}