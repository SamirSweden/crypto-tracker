package com.example.elitedev.ui.theme.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.lazy

object  RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api-for-the-crypto-app.vercel.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ApiService = retrofit.create(ApiService::class.java)
}