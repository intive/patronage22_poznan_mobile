package database

import androidx.room.*
import com.intive.patronage22.intivi.database.Favorites
import com.intive.patronage22.intivi.database.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM User WHERE username LIKE :username AND " +
            "password LIKE :password LIMIT 1")
    fun findUser(username: String, password: String): User

    @Query("SELECT * FROM user WHERE username LIKE :usernameQuery ")
    fun findAllByName(usernameQuery: String): List<User>

    @Query("SELECT * FROM User WHERE uid =:userID")
    fun getUser(userID: Int): User

    @Query("SELECT * FROM User WHERE username =:vUsername")
    fun getUserByUsername(vUsername: String): User

    @Query("SELECT * FROM User WHERE email =:vEmail")
    fun getUserByEmail(vEmail: String): User

    //@Update(onConflict = OnConflictStrategy.REPLACE)
    //fun updateUser(user: User)

    //@Query("UPDATE User SET login_date=:new_date WHERE uid = :id")
    //fun update(new_date: Long?, id: Int)

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}

@Dao
interface FavoritesDao{
    @Query("SELECT * FROM `Favorites movies`")
    fun getAll(): List<Favorites>

    @Insert
    fun insert(favorites: Favorites)

    @Delete
    fun delete(favorites: Favorites)

    @Query("SELECT IsFavorites from `favorites movies` where movieID=:vMovieId")
    fun isFavorite(vMovieId:Long): Boolean
}