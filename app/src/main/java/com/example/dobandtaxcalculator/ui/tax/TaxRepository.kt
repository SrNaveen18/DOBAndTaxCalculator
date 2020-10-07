package com.example.dobandtaxcalculator.ui.tax

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.dobandtaxcalculator.helper.ScreenType
import com.example.dobandtaxcalculator.room.ImageDao
import com.example.dobandtaxcalculator.room.ImageDatabase
import com.example.dobandtaxcalculator.room.ImageItem
import com.example.dobandtaxcalculator.webservice.ApiClient
import com.example.dobandtaxcalculator.webservice.ApiStories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.InputStream

class TaxRepository(private val application: Application) {
    private val imageDao: ImageDao?
    private val apiStories: ApiStories?

    init {
        val db = ImageDatabase.getDatabase(application)
        imageDao = db?.getUserDao()
        apiStories = ApiClient.create()
    }

    fun getAllData(): LiveData<List<ImageItem>>? {
        return imageDao?.getAllData(ScreenType.TAX.name)
    }


    suspend fun downloadImage() {
        val response = withContext(Dispatchers.IO) {
            apiStories?.downloadImage()
        }
        response?.byteStream()?.saveToFile("${System.currentTimeMillis()}.jpg")
    }

    private suspend fun InputStream.saveToFile(file: String) = withContext(Dispatchers.IO){
        use { input ->
            File(application.getExternalFilesDir(null)?.absolutePath, file).outputStream()
                .use { output ->
                    input.copyTo(output)
                    addData(
                        ImageItem(
                            imagePath = application.getExternalFilesDir(null)?.absolutePath + "/" + file,
                            from = ScreenType.TAX.name
                        )
                    )
                }
        }
    }

    private suspend fun addData(imageItem: ImageItem) {
        withContext(Dispatchers.IO) {
            imageDao?.insert(imageItem)
        }
    }
}