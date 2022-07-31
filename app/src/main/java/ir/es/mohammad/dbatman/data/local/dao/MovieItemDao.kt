package ir.es.mohammad.dbatman.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.es.mohammad.dbatman.model.MovieItem

@Dao
interface MovieItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieItem>)

    @Query("SELECT * FROM ${MovieItem.TABLE_NAME}")
    fun getMovies(): List<MovieItem>
}