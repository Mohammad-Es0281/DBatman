package ir.es.mohammad.dbatman.data.remote

import ir.es.mohammad.dbatman.model.MovieItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("")
    suspend fun getMovies(@Query("s") query: String? = null): Response<ArrayList<MovieItem>>

    @GET("")
    suspend fun getMovie(@Query("i") id: String): Response<MovieItem>
}