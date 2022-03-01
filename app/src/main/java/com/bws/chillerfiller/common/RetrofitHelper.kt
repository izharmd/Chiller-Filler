package com.bws.chillerfiller.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
   // private const val BASE_URL = "https://quotable.io/"
   private const val BASE_URL = "http://bigbazaar.uk/Service/BigBazaarService.svc/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}