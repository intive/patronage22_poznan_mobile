package com.intive.patronage22.intivi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.intive.patronage22.intivi.database.User
import com.intive.patronage22.intivi.database.UserDao

@Database(entities = [User::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}