package com.example.elitedev.ui.theme.data

import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {

    @GET("prices")
    suspend fun get_prices(
        @Query("limit") limit: Int = 50
    ): List<CoinDto>
}