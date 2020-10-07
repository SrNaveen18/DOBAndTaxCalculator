package com.example.dobandtaxcalculator.helper

import com.example.dobandtaxcalculator.model.AgeItems
import java.text.SimpleDateFormat
import java.util.*

object AgeCalculator {

    fun calculateAge(strDate: String): AgeItems {
        var years = 0
        var months = 0
        var days = 0
        try {
            val dateFormat = SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault())
            val birthDate = dateFormat.parse(strDate)

            //create calendar object for birth day
            val birthDay = Calendar.getInstance()
            birthDate?.let {
                birthDay.timeInMillis = birthDate.time
                //create calendar object for current day
                val currentTime = System.currentTimeMillis()
                val now = Calendar.getInstance()
                now.timeInMillis = currentTime
                //Get difference between years
                years = now[Calendar.YEAR] - birthDay[Calendar.YEAR]
                val currMonth = now[Calendar.MONTH] + 1
                val birthMonth = birthDay[Calendar.MONTH] + 1

                //Get difference between months
                months = currMonth - birthMonth

                //if month difference is in negative then reduce years by one and calculate the number of months.
                if (months < 0) {
                    years--
                    months = 12 - birthMonth + currMonth
                    if (now[Calendar.DATE] < birthDay[Calendar.DATE]) months--
                } else if (months == 0 && now[Calendar.DATE] < birthDay[Calendar.DATE]) {
                    years--
                    months = 11
                }
                //Calculate the days
                if (now[Calendar.DATE] > birthDay[Calendar.DATE]) days =
                    now[Calendar.DATE] - birthDay[Calendar.DATE] else if (now[Calendar.DATE] < birthDay[Calendar.DATE]) {
                    val today = now[Calendar.DAY_OF_MONTH]
                    now.add(Calendar.MONTH, -1)
                    days =
                        now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay[Calendar.DAY_OF_MONTH] + today
                } else {
                    days = 0
                    if (months == 12) {
                        years++
                        months = 0
                    }
                }
                if (currMonth > birthMonth) {
                    if (birthDay[Calendar.DATE] > now[Calendar.DATE]) {
                        months -= 1
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return AgeItems(years, months, days)
    }
}