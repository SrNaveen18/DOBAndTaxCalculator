package com.example.dobandtaxcalculator.ui.tax

import android.app.Application
import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.dobandtaxcalculator.base.BaseViewModel
import com.example.dobandtaxcalculator.helper.TaxCalculator
import kotlinx.coroutines.launch

class TaxViewModel(application: Application) : BaseViewModel(application), Runnable {

    private val repository = TaxRepository(application)
    private val handler = Handler()
    var incomeAmount = MutableLiveData<String>()
    var taxAmount = MutableLiveData<String>()

    init {
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

    fun onClickCalculateTax() {
        incomeAmount.value?.let {
            calculateTax(it.toLong())
        }
    }

    private fun calculateTax(amount: Long) {
        taxAmount.value = TaxCalculator.calculateTax(amount).toString()
    }

    override fun onCleared() {
        handler.removeCallbacks(this)
        super.onCleared()
    }
}