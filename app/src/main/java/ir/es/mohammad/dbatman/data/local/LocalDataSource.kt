package ir.es.mohammad.dbatman.data.local

import ir.es.mohammad.dbatman.data.local.dao.MovieDetailsDao
import ir.es.mohammad.dbatman.data.local.dao.MovieItemDao
import ir.es.mohammad.dbatman.model.MovieDetails
import ir.es.mohammad.dbatman.model.MovieItem
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val movieItemDao: MovieItemDao,
    private val movieDetailsDao: MovieDetailsDao
) : ILocalDataSource {
    override suspend fun getMovies(): ArrayList<MovieItem> {
        return ArrayList(movieItemDao.getMovies())
    }

    override suspend fun insertMovies(movies: ArrayList<MovieItem>) {
        return movieItemDao.insertMovies(movies)
    }

    override suspend fun getMovie(id: String): MovieDetails {
        return movieDetailsDao.getMovie(id)
    }

    override suspend fun insertMovie(movie: MovieDetails) {
        return movieDetailsDao.insertMovie(movie)
    }
}