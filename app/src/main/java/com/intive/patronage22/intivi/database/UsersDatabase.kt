package com.intive.patronage22.intivi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.intive.patronage22.intivi.database.User
import com.intive.patronage22.intivi.database.UserDao

@Database(entities = [User::class], version = 4)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

//    companion object {
//        private var INSTANCE: UsersDatabase? = null
//        fun getDatabase(context: Context): UsersDatabase {
//            if (INSTANCE == null) {
//                synchronized(this) {
//                    INSTANCE =
//                        Room.databaseBuilder(context,UsersDatabase::class.java, "UsersDatabase")
//                            .build()
//                }
//            }
//            return INSTANCE!!
//        }
//    }
}