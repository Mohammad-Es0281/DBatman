package ir.es.mohammad.dbatman.data.remote

import ir.es.mohammad.dbatman.model.MovieDetails
import ir.es.mohammad.dbatman.model.MovieItem
import ir.es.mohammad.dbatman.util.Result

interface IRemoteDataSource {
    suspend fun getBatmanMovies(): Result<ArrayList<MovieItem>>

    suspend fun getMovie(id: String): Result<MovieDetails>
}