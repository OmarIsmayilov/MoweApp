package com.omarismayilov.movaapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omarismayilov.movaapp.data.model.local.MovieLocalModel

@Dao
interface MyListDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addList(movie: MovieLocalModel)

    @Query("SELECT * FROM my_list")
    suspend fun getListData(): List<MovieLocalModel>

    @Query("DELETE FROM my_list WHERE id=:id")
    suspend fun deleteItem(id: Int)



}