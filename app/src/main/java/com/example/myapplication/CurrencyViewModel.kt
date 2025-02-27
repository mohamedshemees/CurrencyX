package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repo.CurrenciesRepoImpl
import com.example.myapplication.utils.ApiResponseValidator
import com.example.myapplication.utils.Country
import com.example.myapplication.utils.CurrenciesInfo
import com.example.myapplication.utils.CurrencyFormatter
import com.example.myapplication.utils.FormatUtils
import com.example.myapplication.utils.ResponseHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Date

class CurrencyViewModel : ViewModel() {

    private val _amount = MutableStateFlow("00.00")
    private val _baseCurrency = MutableStateFlow("AUD")
    private val _targetCurrency = MutableStateFlow("AUD")

    private val currenciesRepoImpl = CurrenciesRepoImpl()
     val currenciesInfo = ArrayList<CurrenciesInfo>()
    private var _date = MutableStateFlow("")
    private var _exchangeRates = MutableStateFlow(mapOf("AUD" to mapOf("AUD" to 0.00)))

    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result

    private var _digitLimit = MutableStateFlow(false)
    var digitLimit: StateFlow<Boolean> = _digitLimit

    private val _uiDate = MutableStateFlow(FormatUtils.getYesterdayFormatted())
    var uiDate: StateFlow<String> = _uiDate

    init {
        observeAmountLimit()
        observeResultChanges()
        observeExchangeRateChanges()
    }
    private fun observeAmountLimit() {
        _amount
            .map { it.length >= 9 }
            .onEach { _digitLimit.value = it }
            .launchIn(viewModelScope)
    }

    private fun observeResultChanges() {
        viewModelScope.launch {
            combine(_amount, _exchangeRates, _targetCurrency) { amount, exchangeRates, targetCurrency ->
                val firstKey = exchangeRates.keys.firstOrNull() ?: return@combine 0.00
                val exchangeRate = exchangeRates[firstKey]?.get(targetCurrency) ?: 0.0
                if (amount.length < 16) {
                    exchangeRate * (amount.toDoubleOrNull() ?: 0.0)
                } else {
                    0.00
                }
            }.collect { newResult ->
                _result.value = CurrencyFormatter.format(newResult,_targetCurrency.value)
            }
        }
    }
    private fun observeExchangeRateChanges() {
        viewModelScope.launch {
            combine(_baseCurrency, _date) {_,_->
            }.collect {
                getCurrencyExchangeRate()
            }
        }
    }
    fun updateDate(newDateMillis: Long) {
        val date = Date(newDateMillis) // Create Date object once
        _date.value = FormatUtils.apiDateFormat.format(date)  // API format
        _uiDate.value = FormatUtils.uiDateFormat.format(date) // UI format
    }
    fun updateAmount(newText: String) {
        if (newText.isNotBlank()) {
            _amount.value = newText
        }
        else{
            _amount.value = "00.00"
        }
    }
    private fun updateData(newData: Map<String, Map<String, Double>>) {
        _exchangeRates.value = newData
    }

    fun updateBaseCurrency(currency: String) {
        _baseCurrency.value = currency

    }

    fun updateTargetCurrency(currency: String) {
        _targetCurrency.value = currency
    }

    fun getCurrencyExchangeRate() = viewModelScope.launch {
        val dataFetched = currenciesRepoImpl.fetchCurreencyExchangRate(_baseCurrency.value, _date.value)
        val responseValidator = ApiResponseValidator().validateResponse(dataFetched.code())
        when (responseValidator) {
            is ResponseHandler.Success -> {
                dataFetched.body()?.let {
                    updateData(it.data)
                }
            }
            is ResponseHandler.Failure -> { /* Handle error */ }
        }
    }

    fun loadCurrencyList(): ArrayList<CurrenciesInfo> {
        val country = Country()
         currenciesInfo.addAll(country.fullCureencyInfo.values)
        return currenciesInfo
    }
}

