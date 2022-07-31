package ir.es.mohammad.dbatman.data

import ir.es.mohammad.dbatman.data.local.LocalDataSource
import ir.es.mohammad.dbatman.data.remote.RemoteDataSource
import ir.es.mohammad.dbatman.model.MovieDetails
import ir.es.mohammad.dbatman.model.MovieItem
import ir.es.mohammad.dbatman.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun getBatmanMovies(): Flow<Result<ArrayList<MovieItem>>> {
        return loadAndSave(
            apiCall = { remoteDataSource.getBatmanMovies() },
            loadFromLocal = { localDataSource.getMovies() },
            saveToLocal = { localDataSource.insertMovies(it) }
        )
    }

    suspend fun getMovie(id: String): Flow<Result<MovieDetails>> {
        return loadAndSave(
            apiCall = { remoteDataSource.getMovie(id) },
            loadFromLocal = { localDataSource.getMovie(id) },
            saveToLocal = { localDataSource.insertMovie(it) }
        )
    }
}