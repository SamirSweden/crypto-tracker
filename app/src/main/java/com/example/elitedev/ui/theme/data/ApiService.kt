package com.example.elitedev.ui.theme.data


import retrofit2.http.Body
import retrofit2.http.POST

interface  ApiService{
    @POST("api/login/user")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}