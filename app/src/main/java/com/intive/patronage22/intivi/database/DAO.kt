package com.intive.patronage22.intivi.database

import androidx.room.*
import com.intive.patronage22.intivi.database.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    suspend fun getUsersList(): List<User>

    @Query("SELECT * FROM User WHERE email LIKE :email AND " +
            "password LIKE :password LIMIT 1")
    suspend fun getUser(email: String, password: String): User

    @Query("SELECT * FROM User WHERE uid =:userID")
    suspend fun getUser(userID: Int): User

    @Query("SELECT * FROM User WHERE email =:email")
    suspend fun getUser(email: String): User

    @Query("UPDATE User SET email=:new_email WHERE uid = :id")
    suspend fun updateEmail(new_email: String?, id: Int)

    @Query("UPDATE User SET username=:new_username WHERE uid = :id")
    suspend fun updateUsername(new_username: String?, id: Int)

    @Query("UPDATE User SET password=:new_password WHERE uid = :id")
    suspend fun updatePassword(new_password: String?, id: Int)

    @Query("UPDATE User SET avatar=:new_avatar WHERE uid = :id")
    suspend fun updateAvatar(new_avatar: Int?, id: Int)

    @Update
    suspend fun updateUser(user: User)

    @Insert (onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUsers(vararg users: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT EXISTS(SELECT * FROM User WHERE email = :email)")
    suspend fun isEmailTaken(email: String): Boolean

    @Query("SELECT EXISTS(SELECT * FROM User WHERE email = :email AND password = :password)")
    suspend fun doesUserExist(email: String, password: String): Boolean
}