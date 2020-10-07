package com.example.dobandtaxcalculator.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "image_item")
data class ImageItem(
    val imagePath: String = "",
    val from : String? = ""
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var empId: Int = 0

}

