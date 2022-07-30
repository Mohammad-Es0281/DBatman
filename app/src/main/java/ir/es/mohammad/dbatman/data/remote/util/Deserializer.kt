package ir.es.mohammad.dbatman.data.remote.util

import android.util.Log
import com.google.gson.JsonObject
import ir.es.mohammad.dbatman.model.MovieItem

object Deserializer {
    fun jsonToMovieItem(jsonObject: JsonObject): List<MovieItem> {
        val moviesJson = jsonObject["Search"].asJsonArray
        val movies = ArrayList(moviesJson.map { it.asJsonObject.movieItemMapper() })
        Log.d("Deserialize", movies.toString())
        return movies
    }

    private fun JsonObject.movieItemMapper(): MovieItem {
        return MovieItem(
            title = this["Title"].asString,
            year = this["Year"].asString,
            id = this["imdbID"].asString,
            type = this["Type"].asString,
            poster = this["Poster"].asString
        )
    }
}