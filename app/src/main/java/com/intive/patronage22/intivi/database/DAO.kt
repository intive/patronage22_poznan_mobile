package com.intive.patronage22.intivi.database

import androidx.room.*
import com.intive.patronage22.intivi.database.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    suspend fun getUsersList(): List<User>

    @Query("SELECT * FROM User WHERE username LIKE :username AND " +
            "password LIKE :password LIMIT 1")
    suspend fun findUser(username: String, password: String): User

    @Query("SELECT * FROM User WHERE uid =:userID")
    suspend fun findUser(userID: Int): User

    @Query("SELECT * FROM User WHERE email =:vEmail")
    suspend fun findUser(vEmail: String): User

    @Insert
    suspend fun insertVarargUser(vararg users: User)

    @Delete
    suspend fun deleteUser(user: User)
}