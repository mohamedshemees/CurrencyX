package com.example.myapplication.utils

import com.example.myapplication.R

class Country {
      val fullCureencyInfo: Map<String, CurrenciesInfo> =
         mapOf(
             "AUD" to CurrenciesInfo("AUD", "Australian Dollar", R.drawable.au),
             "BGN" to CurrenciesInfo("BGN", "Bulgarian Lev", R.drawable.gb),
             "BRL" to CurrenciesInfo("BRL", "Brazilian Real", R.drawable.br),
             "CAD" to CurrenciesInfo("CAD", "Canadian Dollar", R.drawable.ca),
             "CHF" to CurrenciesInfo("CHF", "Swiss Franc", R.drawable.sek),
             "CNY" to CurrenciesInfo("CNY", "Chinese Yuan", R.drawable.cn),
             "CZK" to CurrenciesInfo("CZK", "Czech Republic Koruna", R.drawable.cz),
             "DKK" to CurrenciesInfo("DKK", "Danish Krone", R.drawable.denemark),
             "EUR" to CurrenciesInfo("EUR", "Euro", R.drawable.eu),
             "GBP" to CurrenciesInfo("GBP", "British Pound", R.drawable.gb),
             "HKD" to CurrenciesInfo("HKD", "Hong Kong Dollar", R.drawable.hk),
             "HRK" to CurrenciesInfo("HRK", "Croatian Kuna", R.drawable.cr),
             "HUF" to CurrenciesInfo("HUF", "Hungarian Forint", R.drawable.hu),
             "IDR" to CurrenciesInfo("IDR", "Indonesian Rupiah", R.drawable.id),
             "INR" to CurrenciesInfo("INR", "Indian Rupee", R.drawable.`in`),
             "ISK" to CurrenciesInfo("ISK", "Icelandic Króna", R.drawable.`is`),
             "JPY" to CurrenciesInfo("JPY", "Japanese Yen", R.drawable.jp),
             "KRW" to CurrenciesInfo("KRW", "South Korean Won", R.drawable.kr),
             "MXN" to CurrenciesInfo("MXN", "Mexican Peso", R.drawable.mx),
             "MYR" to CurrenciesInfo("MYR", "Malaysian Ringgit", R.drawable.my),
             "NOK" to CurrenciesInfo("NOK", "Norwegian Krone", R.drawable.no),
             "NZD" to CurrenciesInfo("NZD", "New Zealand Dollar", R.drawable.nz),
             "PHP" to CurrenciesInfo("PHP", "Philippine Peso", R.drawable.ph),
             "PLN" to CurrenciesInfo("PLN", "Polish Zloty", R.drawable.pl),
             "RON" to CurrenciesInfo("RON", "Romanian Leu", R.drawable.romania),
             "RUB" to CurrenciesInfo("RUB", "Russian Ruble", R.drawable.ru),
             "SEK" to CurrenciesInfo("SEK", "Swedish Krona", R.drawable.sek),
             "SGD" to CurrenciesInfo("SGD", "Singapore Dollar", R.drawable.sg),
             "THB" to CurrenciesInfo("THB", "Thai Baht", R.drawable.th),
             "TRY" to CurrenciesInfo("TRY", "Turkish Lira", R.drawable.tr),
             "USD" to CurrenciesInfo("USD", "United States Dollar", R.drawable.us),
             "ZAR" to CurrenciesInfo("ZAR", "South African Rand", R.drawable.za)
         )
 }


data class CurrenciesInfo(
    val appreviation:String,
    val country:String,
    val flag:Int,
)