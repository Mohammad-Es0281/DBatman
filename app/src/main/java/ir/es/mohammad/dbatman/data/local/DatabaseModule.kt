package ir.es.mohammad.dbatman.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.es.mohammad.dbatman.data.local.dao.MovieDetailsDao
import ir.es.mohammad.dbatman.data.local.dao.MovieItemDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            MovieDataBase::class.java,
            MovieDataBase::class.java.simpleName
        ).build()

    @Provides
    @Singleton
    fun provideMovieItemDao(movieDataBase: MovieDataBase) = movieDataBase.movieItemDao()

    @Provides
    @Singleton
    fun provideMovieDetailsDao(movieDataBase: MovieDataBase) = movieDataBase.movieDetailsDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(movieItemDao: MovieItemDao, movieDetailsDao: MovieDetailsDao): ILocalDataSource {
        return LocalDataSource(movieItemDao = movieItemDao, movieDetailsDao = movieDetailsDao)
    }
}