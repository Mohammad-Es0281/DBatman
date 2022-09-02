package ir.es.mohammad.dbatman.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = MovieItem.TABLE_NAME)
data class MovieItem(
    @SerializedName("imdbID")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: String,
    @SerializedName("Title")
    @ColumnInfo(name = "title")
    val title: String,
    @SerializedName("Poster")
    @ColumnInfo(name = "poster")
    val poster: String,
    @SerializedName("Type")
    @ColumnInfo(name = "type")
    val type: String,
    @SerializedName("Year")
    @ColumnInfo(name = "year")
    val year: String
) {
    companion object {
        const val TABLE_NAME = "movie_item"
    }
}