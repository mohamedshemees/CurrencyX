package com.example.data.repo

import android.util.Log
import com.example.data.remote.ApiService
import com.example.domain.entity.ResponseWrapper
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class CurrenciesRepoImpl {

    suspend fun fetchCurreencyExchangRate(
        basecurrency: String,
        datepicked: String
    ): Response<ResponseWrapper> {

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.freecurrencyapi.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val response= apiService.getCurrencyData(basecurrency, datepicked)
        return response

    }
}

