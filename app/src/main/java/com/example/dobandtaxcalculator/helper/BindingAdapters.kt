package com.example.dobandtaxcalculator.helper

import android.app.DatePickerDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import java.util.*

object BindingAdapters {

    @BindingAdapter("setDatePickerYear")
    @JvmStatic
    fun setDatePicker(appCompatEditText: AppCompatEditText, year: Int) {
        val c = Calendar.getInstance()
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        appCompatEditText.setOnClickListener {
            val datePickerDialog = DatePickerDialog(appCompatEditText.context,
                { _, year, monthOfYear, dayOfMonth ->
                    val month = monthOfYear + 1
                    appCompatEditText.setText("$dayOfMonth/$month/$year")
                }, year, month, day)

            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
    }
}