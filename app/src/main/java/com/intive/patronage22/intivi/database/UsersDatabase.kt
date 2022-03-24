package database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 3)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}