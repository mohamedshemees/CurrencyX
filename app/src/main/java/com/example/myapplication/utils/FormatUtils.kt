package com.example.myapplication.utils

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Currency
import java.util.Locale
import java.util.TimeZone

object FormatUtils {

    val apiDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val uiDateFormat = SimpleDateFormat("MMM d, y", Locale.ENGLISH).apply {
        timeZone = TimeZone.getDefault()
    }
    private fun getYesterdayCalendar(): Calendar {
        return Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, -1)
        }
    }

    fun getYesterdayFormatted(): String {
        return uiDateFormat.format(getYesterdayCalendar().time)
    }

    fun getYesterdayInMillis(): Long {
        return getYesterdayCalendar().timeInMillis
    }
}

object CurrencyFormatter {
    fun format(amount: Double, currencyCode: String, locale: Locale = Locale.US): String {
        val currency = Currency.getInstance(currencyCode)

        val normalizedAmount = BigDecimal(amount.toString()).toPlainString().toDouble()

        var currencySymbol = currency.getSymbol(locale)

        val symbols = DecimalFormatSymbols(locale).apply {
            currencySymbol = "$currencySymbol "
        }

        val formatter = DecimalFormat("¤#,##0.00", symbols)

        return formatter.format(normalizedAmount)
    }
}