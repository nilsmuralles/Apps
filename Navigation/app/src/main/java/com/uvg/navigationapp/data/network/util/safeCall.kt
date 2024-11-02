package com.uvg.navigationapp.data.network.util

import com.uvg.navigationapp.domain.network.util.DataError
import io.ktor.client.statement.HttpResponse
import com.uvg.navigationapp.domain.network.util.Result
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, DataError> {
    val response = try {
        execute()
    } catch(e: UnresolvedAddressException) {
        return Result.Error(DataError.NO_INTERNET)
    } catch(e: SerializationException) {
        return Result.Error(DataError.GENERIC_ERROR)
    } catch(e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(DataError.GENERIC_ERROR)
    }

    return responseToResult(response)
}