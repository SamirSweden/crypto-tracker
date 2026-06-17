package com.example.elitedev.data.repository

import com.example.elitedev.data.RetrofitClient
import com.example.elitedev.data.model.Message

class ChatRepository {

    suspend fun getMessages(): List<Message>{
        return RetrofitClient.api.getMessages();
    }

    suspend fun sendMessages(text: String) {
        RetrofitClient.api.sendMessages(sendMessageRequest(text))
    }
}