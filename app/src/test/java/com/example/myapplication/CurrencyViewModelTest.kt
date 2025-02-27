package com.example.myapplication

import com.example.data.repo.CurrenciesRepoImpl
import com.example.domain.entity.ResponseWrapper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.example.myapplication.utils.ResponseHandler
import kotlinx.coroutines.flow.flowOf
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response


class CurrencyViewModelTest {


    private lateinit var currencyViewModel : CurrencyViewModel

    @Before
    fun setUp() {
        currencyViewModel = CurrencyViewModel()
    }

    @Test
    fun updateAmount() = runTest {
        val amount = "100.0"
        currencyViewModel.updateAmount(amount)

        assertEquals(amount,currencyViewModel.amount.first())
    }
    @Test
    fun updateBaseCurrency() = runTest {
        val currency = "USD"
        currencyViewModel.updateBaseCurrency(currency)

        assertEquals(currency,currencyViewModel.baseCurrency.value)
    }
    @Test
    fun updateTargetCurrency() = runTest {
        val currency = "USD"
        currencyViewModel.updateTargetCurrency(currency)
        assertEquals(currency,currencyViewModel.targetCurrency.value)
    }

    @Test
    fun fetchExchangeRates() = runTest {
        val repo:CurrenciesRepoImpl=mock()

        // Simulate the required state
        currencyViewModel.updateBaseCurrency("USD")
        currencyViewModel.updateDate(1672531200000L) // January 1, 2023

        val exchangeRates = mapOf("USD" to mapOf("EUR" to 0.85))

        // Mock the repository response


        // When
        currencyViewModel.getCurrencyExchangeRate()

        // Then
        assertEquals(exchangeRates, currencyViewModel.data.first())
    }


}