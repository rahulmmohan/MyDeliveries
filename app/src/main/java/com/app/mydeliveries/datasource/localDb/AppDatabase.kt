package com.app.mydeliveries.datasource.localDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.mydeliveries.datasource.model.Delivery

@Database(entities = [(Delivery::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun deliveryDao(): DeliveriesDao

    companion object {
        private const val DATABASE = "myDeliveries"
        private var INSTANCE: AppDatabase? = null
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE)
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}