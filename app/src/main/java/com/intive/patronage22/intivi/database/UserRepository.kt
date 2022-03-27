package com.intive.patronage22.intivi.database

import android.content.Context
import android.util.Log
import androidx.room.Room

class UserRepository {

    lateinit var dao: UserDao

    fun initialize(applicationContext: Context) {
        dao = Room.databaseBuilder(
            applicationContext,
            UsersDatabase::class.java, "UsersDatabase"
        ).build().userDao()
    }

    suspend fun getUsersList(): List<User> {
        return dao.getUsersList()
    }

    suspend fun findUser(userID: Int): User {
        return dao.findUser(userID)
    }

    suspend fun findUser(username: String, password: String): User {
        return dao.findUser(username, password)
    }

    suspend fun findUser(email: String): User {
        return dao.findUser(email)
    }

    suspend fun insertVarargUser(vararg user: User) {
        return dao.insertVarargUser(*user)
    }

    suspend fun deleteUser(user: User) {
        return dao.deleteUser(user)
    }
}


