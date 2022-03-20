package com.intive.patronage22.intivi.database

import android.content.Context
import androidx.room.Room
import database.FavoritesDao
import database.UsersDatabase

class FavoritesRepository {
    lateinit var dao: FavoritesDao

    fun initialize(applicationContext: Context) {
        dao = Room.databaseBuilder(
            applicationContext,
            UsersDatabase::class.java, "UsersDatabase"
        ).allowMainThreadQueries()
            .build()
            .favoritesDao()
    }

    fun getFavorites(): List<Favorites> {
        return dao.getAll()
    }
    fun deleteFavorites(favorites: Favorites){
        return dao.delete(favorites)
    }
    fun isFavorite(movieId: Long):Boolean{
        return dao.isFavorite(movieId)
    }
    fun insertFav(favorites: Favorites){
        return dao.insert(favorites)
    }
}