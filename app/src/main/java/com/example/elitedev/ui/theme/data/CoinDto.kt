package com.example.elitedev.ui.theme.data

import com.google.gson.annotations.SerializedName


data class CoinDto (
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,


    @SerializedName("price_usd")
    val priceUsd: Double,

    @SerializedName("change24h")
    val change24h: Double
)





