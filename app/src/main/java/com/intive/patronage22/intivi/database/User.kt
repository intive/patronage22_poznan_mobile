package com.intive.patronage22.intivi.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "username") var username: String?,
    @ColumnInfo(name = "password") var password: String?,
    @ColumnInfo(name = "email") var email: String?,
    @ColumnInfo(name = "dateCreated") val dateCreated: Long,
    @ColumnInfo(name = "avatar") var avatar: Int? = 1
)