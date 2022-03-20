package com.intive.patronage22.intivi.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorites movies")
class Favorites (
    @PrimaryKey(autoGenerate = true) var fid: Int,
    @ColumnInfo(name = "movieID") var movieID: Long,
    @ColumnInfo(name = "IsFavorites") var isFavorite: Boolean
)