package com.example.elitedev.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApiService {
    @GET("api/v3/coins/markets?vs_currency=usd")
    suspend fun getCoins(@Query("vs_currency") currency: String = "usd"): List<Coin>
}


object RetrofitClient{
    private const val BASE_URL = "https://api.coingecko.com/"

    val api: CoinApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApiService::class.java)
    }
}


