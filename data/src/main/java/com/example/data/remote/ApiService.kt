package com.example.data.remote
import android.annotation.SuppressLint
import com.example.domain.entity.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService{
@GET("historical?&apikey=&base_currency&date")

suspend fun getCurrencyData(@Query("base_currency")basecurrency:String,
  @SuppressLint("NewApi")  @Query("date")datepicked: String="")
: ResponseWrapper?
}
