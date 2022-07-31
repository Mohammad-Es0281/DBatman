package ir.es.mohammad.dbatman.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.es.mohammad.dbatman.model.MovieDetails

@Dao
interface MovieDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: MovieDetails)

    @Query("SELECT * FROM ${MovieDetails.TABLE_NAME} WHERE id = :id")
    fun getMovie(id: String): MovieDetails
}