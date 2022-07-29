package ir.es.mohammad.dbatman.data.remote

import ir.es.mohammad.dbatman.model.MovieItem
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val movieApi: MovieApi): IRemoteDataSource {
    override suspend fun getBatmanMovies(): Response<ArrayList<MovieItem>> {
        return movieApi.getMovies("batman")
    }

    override suspend fun getMovie(id: String): Response<MovieItem> {
        return movieApi.getMovie(id)
    }

}