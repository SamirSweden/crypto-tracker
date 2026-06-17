package com.example.elitedev.data.api

import com.example.elitedev.data.model.Message
import com.example.elitedev.data.model.SendMessageRequest
import retrofit2.Response
import retrofit2.http.*


interface ChatApi {

    @GET("/api/chat")
    suspend fun getMessages(): List<Message>

    @POST("/api/chat")
    suspend fun sendMessage(@Body request: SendMessageRequest) : Response<Unit>
}