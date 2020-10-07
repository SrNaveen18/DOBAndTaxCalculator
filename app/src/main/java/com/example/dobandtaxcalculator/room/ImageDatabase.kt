package com.example.dobandtaxcalculator.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ImageItem::class], version = 1, exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {

    abstract fun getUserDao(): ImageDao

    companion object {

        @Volatile
        private var INSTANCE: ImageDatabase? = null

        fun getDatabase(context: Context): ImageDatabase? {
            if (INSTANCE == null) {
                synchronized(ImageDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ImageDatabase::class.java, "image_database")
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}


