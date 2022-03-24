package database

import android.content.Context
import android.util.Log
import androidx.room.Room

class UserRepository {

    lateinit var dao: UserDao

    fun initialize(applicationContext: Context) {
        dao = Room.databaseBuilder(
            applicationContext,
            UsersDatabase::class.java, "UsersDatabase"
        ).allowMainThreadQueries()
            .build()
            .userDao()
    }

    fun getUsers(): List<User> {
        return dao.getAll()
    }

    fun getUser(userID: Int): User {
        return dao.getUser(userID)
    }

    fun getUserByUsername(vUsername: String): User {
        return dao.getUserByUsername(vUsername)
    }

    fun getUserByEmail(vEmail: String): User {
        return dao.getUserByEmail(vEmail)
    }

    //fun updateUser() {
    //    return dao.updateUser(User)
    //}

    //fun update(date: Long?, uid: Int){
    //    return dao.update(date,uid)
    //}

    fun findUser(username: String, password: String): User {
        Log.d("log", "Attempted to find user: $username, $password")
        return dao.findUser(username, password)
    }

    fun addUser(user: User) {
        Log.d("log", "Attempted to add user")
        return dao.insertAll(user)
    }
}


