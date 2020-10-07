package com.example.dobandtaxcalculator.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ImageDao {
    @Insert
    fun insert(imagePath: ImageItem?)

    @Query("DELETE FROM image_item")
    fun deleteAllData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(order: List<ImageItem>)

    @Query("SELECT * FROM image_item ORDER BY imagePath ASC")
    fun getAllData(): LiveData<List<ImageItem>>

    @Query("SELECT * FROM image_item WHERE `from` LIKE :isFrom")
    fun getAllData(isFrom : String): LiveData<List<ImageItem>>

}