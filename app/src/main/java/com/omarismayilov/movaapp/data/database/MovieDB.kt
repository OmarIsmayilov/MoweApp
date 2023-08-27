package com.omarismayilov.movaapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omarismayilov.movaapp.data.model.local.MovieLocalModel


@Database(entities = [MovieLocalModel::class], version = 1, exportSchema = false)
abstract class MovieDB : RoomDatabase() {
    abstract fun myListDAO() : MyListDAO
}