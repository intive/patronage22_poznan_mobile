package com.intive.patronage22.intivi.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Favorites (
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "movieID") var movieID: Long,
)