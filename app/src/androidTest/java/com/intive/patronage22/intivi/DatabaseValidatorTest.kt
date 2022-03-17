package com.intive.patronage22.intivi

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import database.User
import database.UserDao
import database.UsersDatabase
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
    fun writeUserAndReadInList() {
        val user = User(1, "george", "password", "e@mail.com")
        userDao.insertAll(user)
        val byName: List<User> = userDao.findAllByName("george")
        val user1: User = byName[0]
        assert(user1 == user)
    }
}