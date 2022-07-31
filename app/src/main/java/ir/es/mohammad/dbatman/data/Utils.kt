package ir.es.mohammad.dbatman.data

import ir.es.mohammad.dbatman.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

suspend inline fun <T> loadAndSave(
    crossinline apiCall: suspend () -> Result<T>,
    crossinline loadFromLocal: suspend () -> T,
    crossinline saveToLocal: suspend (T) -> Unit
): Flow<Result<T>> {
    return flow<Result<T>> {
        emit(Result.Loading())
        val networkResponse = apiCall()
        if (networkResponse.isSuccess) {
            val networkData = networkResponse.data!!
            saveToLocal(networkData)
            emit(Result.Success(networkData))
        } else {
            val localResult = loadFromLocal()
            localResult?.let { emit(Result.Success(localResult)) }
                ?: emit(Result.Error(Throwable("Please Connect to Internet!")))
        }
    }.catch { emit(Result.Error(Throwable("Something went wrong!"))) }.flowOn(Dispatchers.IO)
}