package com.example.dobandtaxcalculator.helper

object TaxCalculator {

    fun calculateTax(amount: Long): Double {
        return when {
            amount <= 300000 -> 0.0
            amount in 300001..500000 -> getTaxBetween3To5Lakhs(amount)
            amount in 500001..1000000 -> getTaxBetween5To10Lakhs(amount)
            amount > 1000000 -> getTaxBetweenAbove10Lakhs(amount)
            else -> 0.0
        }
    }

    private fun getTaxBetween3To5Lakhs(amount: Long): Double {
        val sub = amount - 300000
        val incomeTax = (sub / 100) * 10
        return addTaxAmount(incomeTax).toDouble()
    }

    private fun getTaxBetween5To10Lakhs(amount: Long): Double {
        val sub = amount - 500000
        val incomeTax = (sub / 100) * 20 + 20000
        return addTaxAmount(incomeTax).toDouble()
    }

    private fun getTaxBetweenAbove10Lakhs(amount: Long): Double {
        val sub = amount - 1000000
        val incomeTax = (sub / 100) * 30 + 120000
        return addTaxAmount(incomeTax).toDouble()
    }

    private fun addTaxAmount(incomeTax: Long): Long {
        val education = (incomeTax / 100) * 2
        val secondary = (incomeTax / 100) * 1
        return incomeTax + education + secondary
    }

}