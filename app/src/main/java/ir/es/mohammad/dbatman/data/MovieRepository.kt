package ir.es.mohammad.dbatman.data

import ir.es.mohammad.dbatman.data.remote.RemoteDataSource
import ir.es.mohammad.dbatman.model.MovieDetails
import ir.es.mohammad.dbatman.model.MovieItem
import ir.es.mohammad.dbatman.data.remote.util.Result
import ir.es.mohammad.dbatman.data.remote.util.apiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class MovieRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getBatmanMovies(): Flow<Result<ArrayList<MovieItem>>> {
        return apiCall { remoteDataSource.getBatmanMovies() }
    }

    suspend fun getMovie(id: String): Flow<Result<MovieDetails>> {
        return apiCall { remoteDataSource.getMovie(id) }
    }
}