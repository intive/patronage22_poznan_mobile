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

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, UsersDatabase::class.java).build()
        userDao = db.userDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeUserAndReadInList() = runBlocking {
        val user = User(1, "george", "password", "e@mail.com")
        userDao.insertVarargUser(user)
        val foundUser: User = userDao.findUser("e@mail.com")
        assert(foundUser == user)
    }
}