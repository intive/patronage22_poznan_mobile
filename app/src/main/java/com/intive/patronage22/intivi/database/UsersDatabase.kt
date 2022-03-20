package database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.intive.patronage22.intivi.database.Favorites
import com.intive.patronage22.intivi.database.User

@Database(entities = [User::class, Favorites::class], version = 3)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun favoritesDao(): FavoritesDao
}