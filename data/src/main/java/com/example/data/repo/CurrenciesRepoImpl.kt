package com.example.data.repo

import android.annotation.SuppressLint
import com.example.data.remote.ApiService
import com.example.domain.entity.ResponseWrapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class CurrenciesRepoImpl {

    @SuppressLint("NewApi")
    suspend fun fetchCurreencyExchangRate(basecurrency: String, datepicked: String): ResponseWrapper? {

        var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.freecurrencyapi.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var apiService = retrofit.create(ApiService::class.java)

        return apiService.getCurrencyData(basecurrency,datepicked)



    }
}