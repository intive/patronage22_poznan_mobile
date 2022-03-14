package database

import android.service.autofill.UserData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM User WHERE username LIKE :username AND " +
            "password LIKE :password LIMIT 1")
    fun findUser(username: String, password: String): User

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