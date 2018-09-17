package com.gladysproject.gladys.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.gladysproject.gladys.database.converter.DateConverter
import com.gladysproject.gladys.database.dao.EventDao
import com.gladysproject.gladys.database.dao.MessageDao
import com.gladysproject.gladys.database.entity.Event
import com.gladysproject.gladys.database.entity.Message

@Database(entities = [Event::class, Message::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class GladysAppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun messageDao(): MessageDao
}

abstract class GladysDb {

    companion object {

        var database: GladysAppDatabase? = null

        fun initializeDatabase(context: Context){
            GladysDb.database = Room.databaseBuilder(context, GladysAppDatabase::class.java, "gladys-app-db")
                    .fallbackToDestructiveMigration()
                    .build()
        }

    }

}