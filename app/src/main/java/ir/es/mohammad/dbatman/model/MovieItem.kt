package ir.es.mohammad.dbatman.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("imdbID")
    val id: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Year")
    val year: String
)