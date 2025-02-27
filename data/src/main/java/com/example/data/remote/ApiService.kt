package com.example.data.remote
import com.example.domain.entity.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService{
@GET("historical?&apikey=fca_live_Q8ouH7Y0K01kjpHV0JlOzL6ftn0TKXL8xbcVEZBq&base_currency&date")
suspend fun getCurrencyData(@Query("base_currency")basecurrency:String,
   @Query("date")datepicked: String="")
: Response<ResponseWrapper>}
