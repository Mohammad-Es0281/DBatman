package ir.es.mohammad.dbatman.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.es.mohammad.dbatman.data.local.dao.MovieDetailsDao
import ir.es.mohammad.dbatman.data.local.dao.MovieItemDao
import ir.es.mohammad.dbatman.model.MovieDetails
import ir.es.mohammad.dbatman.model.MovieItem

@Database(entities = [MovieItem::class, MovieDetails::class], exportSchema = false, version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieItemDao(): MovieItemDao

    abstract fun movieDetailsDao(): MovieDetailsDao
}