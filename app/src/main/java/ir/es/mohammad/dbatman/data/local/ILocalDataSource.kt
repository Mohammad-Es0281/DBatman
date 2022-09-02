package ir.es.mohammad.dbatman.data.local

import ir.es.mohammad.dbatman.model.MovieDetails
import ir.es.mohammad.dbatman.model.MovieItem
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun getMovies(): ArrayList<MovieItem>

    suspend fun insertMovies(movies: ArrayList<MovieItem>)

    suspend fun insertMovie(movie: MovieDetails)

    suspend fun getMovie(id: String): MovieDetails
}