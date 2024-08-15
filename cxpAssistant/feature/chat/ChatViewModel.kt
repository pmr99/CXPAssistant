/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.ai.cxpAssistant.feature.chat

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import com.google.ai.cxpAssistant.PostData
import com.google.ai.cxpAssistant.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatViewModel(
    generativeModel: GenerativeModel
) : ViewModel() {
    private val chat = generativeModel.startChat(
        history = listOf(
            content(role = "model") { text("Great to meet you. What would you like to know?") }
        )
    )
    private val apiService = RetrofitClient.api
    private val chatSummary = mutableStateOf("model says great to meet you.")
    private val genAI = generativeModel
    private val RAGResponse = mutableStateOf("")

    private val _uiState: MutableStateFlow<ChatUiState> =
        MutableStateFlow(ChatUiState(chat.history.map { content ->
            // Map the initial messages
            ChatMessage(
                text = content.parts.first().asTextOrNull() ?: "",
                participant = if (content.role == "user") Participant.USER else Participant.MODEL,
                isPending = false
            )
        }))
    val uiState: StateFlow<ChatUiState> =
        _uiState.asStateFlow()

    suspend fun summarizeChat(userMessage: String) {
        val summary = chatSummary.value
        val response =
            genAI.generateContent("Chat Summary:$summary. The user asks the following: $userMessage, and the model responds as follows: ${RAGResponse.value}. Update the chat summary").text
        if (response != null) {
            chatSummary.value = response
        }

    }

    fun sendMessage(userMessage: String) {
        // Add a pending message
        _uiState.value.addMessage(
            ChatMessage(
                text = userMessage,
                participant = Participant.USER,
                isPending = true
            )
        )
        val postData: PostData

        if (userMessage.contains("check-in: ")) {
            postData = PostData(message = "", remove = userMessage.substring(10))
        } else {
            val RAGQuery =
                "Chat Summary: ${chatSummary.value}. User asks the following: $userMessage"
            postData = PostData(message = RAGQuery, remove = "")
        }

        viewModelScope.launch {
            apiService.sendPost(postData).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()?.string()
                        RAGResponse.value = responseBody.toString()
                        _uiState.value.replaceLastPendingMessage()
                        _uiState.value.addMessage(
                            ChatMessage(
                                text = RAGResponse.value,
                                participant = Participant.MODEL,
                                isPending = false
                            )
                        )
                        viewModelScope.launch { summarizeChat(userMessage = userMessage) }
                    } else {
                        Log.e("MainActivity", "Home: Failed to fetch")
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("MainActivity", "Home: Failed to fetch", t)
                }
            })
        }

    }
}