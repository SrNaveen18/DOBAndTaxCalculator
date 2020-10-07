package com.example.dobandtaxcalculator.ui.dob

import android.app.Application
import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.dobandtaxcalculator.base.BaseViewModel
import com.example.dobandtaxcalculator.helper.AgeCalculator
import com.example.dobandtaxcalculator.helper.Constants
import com.example.dobandtaxcalculator.model.AgeItems
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DobViewModel(application: Application) : BaseViewModel(application), Runnable {

    private val repository = DobRepository(application)
    private val handler = Handler()
    private val c: Calendar = Calendar.getInstance()
    var year = MutableLiveData<Int>()
    var dateOfBirth = MutableLiveData<String>()
    var ageItems = MutableLiveData<AgeItems>()
    var age = MutableLiveData<String>()

    init {
        year.value = c.get(Calendar.YEAR)
        handler.postDelayed(this, 8000)
    }

    fun getImageList() = liveData {
        val data = repository.getAllData()
        data?.let {
            emitSource(it)
        }
    }

    override fun run() {
        repeatTask()
        handler.postDelayed(this, 8000)
    }

    private fun repeatTask() {
        viewModelScope.launch {
            repository.downloadImage()
        }
    }

    override fun onCleared() {
        handler.removeCallbacks(this)
        super.onCleared()
    }

    fun calculateAge(strDate: String) {
        ageItems.value = AgeCalculator.calculateAge(strDate)
    }

    fun createAgeString(items: AgeItems, years: String, months: String, days: String): String {
        return "${items.years} $years ${items.months} $months ${items.days} $days"
    }
}