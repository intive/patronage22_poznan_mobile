package com.intive.patronage22.intivi

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.intive.patronage22.intivi.database.User
import com.intive.patronage22.intivi.database.UserDao
import com.intive.patronage22.intivi.database.UsersDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class DatabaseValidatorTest {
    private lateinit var userDao: UserDao
    private lateinit var db: UsersDatabase
    private val user = User(1, "george", "password", "e@mail.com", System.currentTimeMillis())
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            context, UsersDatabase::class.java).build()
        userDao = db.userDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertUserAndCompare() = runBlocking {
        userDao.insertUsers(user)
        val foundUser: User = userDao.getUser("e@mail.com")
        assert(foundUser == user)
    }

    @Test
    fun changePassword() = runBlocking {
        userDao.insertUsers(user)
        userDao.updatePassword("new_password", userDao.getUser("e@mail.com").uid)
        assert(userDao.getUser("e@mail.com").password == "new_password")
    }

    @Test
    fun checkEmailTaken() = runBlocking {
        userDao.insertUsers(user)
        assert(userDao.isEmailTaken("e@mail.com"))
    }
}