package com.intive.patronage22.intivi.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.*

class UserRepository {

    private lateinit var dao: UserDao


        fun initialize(applicationContext: Context){
            dao = Room.databaseBuilder(
                applicationContext,
                UsersDatabase::class.java, "UsersDatabase"
            ).fallbackToDestructiveMigration().build().userDao()
        }

    suspend fun getUsersList(): List<User> {
        return dao.getUsersList()
    }

    fun getUsersList(scope: CoroutineScope): List<User>? {
        var users: List<User>? = null
        scope.launch(Dispatchers.IO){
            scope.async{
                users = dao.getUsersList()
                return@async users
            }.await()
        }
        return users
    }

    suspend fun findUser(userID: Int): User {
        return dao.getUser(userID)
    }

    fun findUser(scope: CoroutineScope, userID: Int): User? {
        var user: User? = null
        scope.launch(Dispatchers.IO){
            scope.async{
                user = dao.getUser(userID)
                return@async user
            }.await()
        }
        return user
    }

    suspend fun findUser(email: String, password: String): User {
        return dao.getUser(email, password)
    }

    fun findUser(scope: CoroutineScope, email: String, password: String): User? {
        var user: User? = null
        scope.launch(Dispatchers.IO){
            scope.async{
                user = dao.getUser(email, password)
                return@async user
            }.await()
        }
        return user
    }

    suspend fun findUser(email: String): User {
        return dao.getUser(email)
    }

    fun findUser(scope: CoroutineScope, email: String): User? {
        var user: User? = null
        scope.launch(Dispatchers.IO){
            scope.async{
                user = dao.getUser(email)
                return@async user
            }.await()
        }
        return user
    }

    suspend fun updateUsername(newUsername: String, Id: Int) {
        return dao.updateUsername(newUsername, Id)
    }

    fun updateUsername(scope: CoroutineScope,newUsername: String, Id: Int) {
        scope.launch(Dispatchers.IO){
            dao.updateEmail(newUsername, Id)
        }
    }

    suspend fun updateEmail(newEmail: String, Id: Int) {
        return dao.updateEmail(newEmail, Id)
    }

    fun updateEmail(scope: CoroutineScope,newEmail: String, Id: Int) {
        scope.launch(Dispatchers.IO){
            dao.updateEmail(newEmail, Id)
        }
    }

    suspend fun updateAvatar(newAvatar: Int, Id: Int) {
        return dao.updateAvatar(newAvatar, Id)
    }

    fun updateAvatar(scope: CoroutineScope,newAvatar: Int, Id: Int) {
        scope.launch(Dispatchers.IO){
            dao.updateAvatar(newAvatar, Id)
        }
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

    suspend fun insertUsers(vararg user: User) {
        return dao.insertUsers(*user)
    }

    fun insertUsers(scope: CoroutineScope, vararg user: User) {
        scope.launch(Dispatchers.IO){
            dao.insertUsers(*user)
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

    suspend fun isEmailTaken(email: String): Boolean{
        return dao.isEmailTaken(email)
    }

    fun isEmailTaken(scope: CoroutineScope, email: String): Boolean {
        var result = false
        scope.launch(Dispatchers.IO){
            scope.async{
                result = dao.isEmailTaken(email)
                return@async result
            }.await()
        }
        return result
    }

    suspend fun doesUserExist(email: String, password: String): Boolean{
        return dao.doesUserExist(email, password)
    }
}


