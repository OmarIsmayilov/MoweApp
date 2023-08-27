package com.omarismayilov.movaapp.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("my_list")
data class MovieLocalModel(
    @PrimaryKey(true)
    var id : Int,
    @ColumnInfo("name")
    var name : String,
    @ColumnInfo("posterPath")
    var posterPath : String,
    @ColumnInfo("rating")
    var rating : Float,
)
