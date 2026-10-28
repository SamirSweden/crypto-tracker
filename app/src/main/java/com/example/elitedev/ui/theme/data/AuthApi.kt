package com.example.elitedev.ui.theme.data


import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface  AuthApi{

    @POST("register")
    suspend fun register(
        @Body data: UserRegister
    ): Response<UserResponse>
}