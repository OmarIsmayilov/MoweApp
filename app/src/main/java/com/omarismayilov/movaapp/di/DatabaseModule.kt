package com.omarismayilov.movaapp.di


import android.content.Context
import androidx.room.Room
import com.omarismayilov.movaapp.data.database.MovieDB
import com.omarismayilov.movaapp.data.database.MyListDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDB {
        return Room.databaseBuilder(
            context,
            MovieDB::class.java,
            "movie_database"
        ).build()
    }


    @Singleton
    @Provides
    fun provideDao(database:MovieDB): MyListDAO {
        return database.myListDAO()
    }


}