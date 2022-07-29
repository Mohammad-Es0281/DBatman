package ir.es.mohammad.dbatman.data.remote.util

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

suspend inline fun <T> apiCall(
    crossinline apiCall: suspend () -> Response<T>
) = flow<Result<T>> {
    emit(Result.Loading())
    val response = apiCall()
    if (response.isSuccessful)
        emit(Result.Success(response.body()!!))
    else {
        val meesage = getResponseMessage(response.code())
        emit(Result.Error(Throwable(meesage)))
    }
}.catch {
    emit(Result.Error(Throwable("Something went wrong!")))
    Log.e("API", it.message.toString())
}.flowOn(Dispatchers.IO)

fun getResponseMessage(responseCode: Int): String {
    return when (responseCode) {
        404 -> "Page Not found!"
        408 -> "Request timeout!"
        503 -> "Service is not Available!"
        else -> "Something went wrong!"
    }
}