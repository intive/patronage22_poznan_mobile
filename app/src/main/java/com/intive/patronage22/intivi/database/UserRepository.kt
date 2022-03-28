package com.intive.patronage22.intivi.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.*

class UserRepository {

    private lateinit var dao: UserDao

    fun initialize(applicationContext: Context) {
        dao = Room.databaseBuilder(
            applicationContext,
            UsersDatabase::class.java, "UsersDatabase"
        ).build().userDao()
    }

    suspend fun getUsersList(): List<User> {
        return dao.getUsersList()
    }

    fun findUser(scope: CoroutineScope): List<User>? {
        var users: List<User>? = null
        runBlocking{
            scope.async(Dispatchers.IO){
                users = dao.getUsersList()
                return@async users
            }.await()
        }
        return users
    }

    suspend fun findUser(userID: Int): User {
        return dao.findUser(userID)
    }

    fun findUser(scope: CoroutineScope, userID: Int): User? {
        var user: User? = null
        runBlocking{
            scope.async(Dispatchers.IO){
                user = dao.findUser(userID)
                return@async user
            }.await()
        }
        return user
    }

    suspend fun findUser(email: String, password: String): User {
        return dao.findUser(email, password)
    }

    fun findUser(scope: CoroutineScope, email: String, password: String): User? {
        var user: User? = null
        runBlocking{
            scope.async(Dispatchers.IO){
                user = dao.findUser(email, password)
                return@async user
            }.await()
        }
        return user
    }

    suspend fun findUser(email: String): User {
        return dao.findUser(email)
    }

    fun findUser(scope: CoroutineScope, email: String): User? {
        var user: User? = null
        runBlocking{
            scope.async(Dispatchers.IO){
                user = dao.findUser(email)
                return@async user
            }.await()
        }
        return user
    }

    suspend fun updatePassword(newPassword: String, Id: Int) {
        return dao.updatePassword(newPassword, Id)
    }

    fun updatePassword(scope: CoroutineScope,newPassword: String, Id: Int) {
        scope.launch(Dispatchers.IO){
            dao.updatePassword(newPassword, Id)
        }
    }

    suspend fun updateUser(user: User) {
        return dao.updateUser(user)
    }

    fun updateUser(scope: CoroutineScope,user: User) {
        scope.launch(Dispatchers.IO){
            dao.updateUser(user)
        }
    }

    suspend fun insertVarargUser(vararg user: User) {
        return dao.insertVarargUser(*user)
    }

    fun insertVarargUser(scope: CoroutineScope, vararg user: User) {
        scope.launch(Dispatchers.IO){
            dao.insertVarargUser(*user)
        }
    }

    suspend fun deleteUser(user: User) {
        return dao.deleteUser(user)
    }

    fun deleteUser(scope: CoroutineScope, user: User) {
        scope.launch(Dispatchers.IO){
            dao.deleteUser(user)
        }
    }
}


