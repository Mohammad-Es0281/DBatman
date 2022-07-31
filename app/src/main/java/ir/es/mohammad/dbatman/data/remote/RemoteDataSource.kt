package ir.es.mohammad.dbatman.data.remote

import android.util.Log
import ir.es.mohammad.dbatman.model.MovieDetails
import ir.es.mohammad.dbatman.model.MovieItem
import ir.es.mohammad.dbatman.util.Result
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val movieApi: MovieApi) : IRemoteDataSource {
    override suspend fun getBatmanMovies(): Result<ArrayList<MovieItem>> {
        return safeApiCall { movieApi.getMovies("batman") }
    }

    override suspend fun getMovie(id: String): Result<MovieDetails> {
        return safeApiCall { movieApi.getMovie(id) }
    }

    private suspend inline fun <T> safeApiCall(crossinline apiCall: suspend () -> Response<T>): Result<T> {
        val result = try {
            val response = apiCall()
            if (response.isSuccessful)
                response.body()?.let { Result.Success(it) }
                    ?: Result.Error(Throwable("Something went wrong"))
            else {
                val meesage = getResponseMessage(response.code())
                Result.Error(Throwable(meesage))
            }
        } catch (ex: UnknownHostException) {
            Result.Error(Throwable(ex.message))
        }
        Log.d("API", "result of request: $result")
        return result
    }

    private fun getResponseMessage(responseCode: Int): String {
        return when (responseCode) {
            404 -> "Page Not found!"
            408 -> "Request timeout!"
            503 -> "Service is not Available!"
            else -> "Something went wrong!"
        }
    }
}