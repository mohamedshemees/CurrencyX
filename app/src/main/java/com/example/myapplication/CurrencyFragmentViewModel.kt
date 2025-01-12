package com.example.myapplication

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.data.repo.CurrenciesRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CurrencyFragmentViewModel: ViewModel() {

    private val _input = MutableStateFlow("1.0")
    val input: StateFlow<String> get()= _input

    private val _result = MutableStateFlow(1.0)
    val result: StateFlow<Double> get()= _result

    private val currenciesRepoImpl = CurrenciesRepoImpl()
    val currenciesInfo = ArrayList<CurreenciesInfo>()

    private var _data = MutableStateFlow(mapOf("USD" to mapOf("USD" to 1.0)))
    var data: MutableStateFlow<Map<String, Map<String,Double>>> = _data

    @SuppressLint("NewApi")

    private var _date= MutableStateFlow("")
    var date: StateFlow<String> = _date



    @SuppressLint("NewApi")
    suspend fun showResult(basecurrency:String="AUD",
                           wantdcurrency:String="AUD",
                           amount:Double=1.0,
                            date:String=""){
        Log.d("wow", "showResult: ${data.value}")

        getCurrencyExchangeRate(basecurrency,date)

                _result.value =
                    data.value[data.value.keys.first()]?.get(wantdcurrency)?.times(amount)!!


    }

    fun updateDate(newDate: String) {
        _date.value = newDate
    }

    fun updateInput(newText: String) {
        if (newText.isNotBlank()) { // Only accept non-null and non-empty strings
            _input.value = newText
        }

    }
    fun updateData(newData: Map<String, Map<String, Double>>) {
        _data.value = newData.toMutableMap()
    }

    suspend fun getCurrencyExchangeRate(basecurrency:String="USD",
                                         datepicked: String=""){
        currenciesRepoImpl.fetchCurreencyExchangRate(basecurrency,datepicked)?.let { updateData( it.data) }
    }


    fun getCurrencyData() {
        val country = Country()
        for (i in country.data.toSortedMap()) {
            currenciesInfo.add(CurreenciesInfo(i.key, i.value))
        }
    }

}