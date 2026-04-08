package com.example.elitedev.data

import com.google.gson.annotations.SerializedName

data class Coin (
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    @SerializedName("current_price") val price: Double
)

// dto model