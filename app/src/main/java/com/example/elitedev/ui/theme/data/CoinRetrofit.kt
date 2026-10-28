package com.example.elitedev.ui.theme.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object CoinRetrofit {
    private const val COIN_API = "https://api-for-the-crypto-app.vercel.app/"

    val coinApi: CoinApi by lazy {
        Retrofit.Builder()
            .baseUrl(COIN_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }
}