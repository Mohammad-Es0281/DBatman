package ir.es.mohammad.dbatman.data.remote

import ir.es.mohammad.dbatman.model.MovieDetails
import ir.es.mohammad.dbatman.model.MovieItem
import retrofit2.Response

interface IRemoteDataSource {
    suspend fun getBatmanMovies(): Response<ArrayList<MovieItem>>

    suspend fun getMovie(id: String): Response<MovieDetails>
}