package com.picpay.desafio.android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.dao.UserDao
import com.picpay.desafio.android.data.model.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class DesafioDatabase : RoomDatabase() {

    abstract fun usersDao(): UserDao

    companion object {
        fun roomDatabaseBuilder(context: Context) =
            Room.databaseBuilder(
                context,
                DesafioDatabase::class.java,
                "desafio_db"
            ).fallbackToDestructiveMigration()
                .build()
    }
}