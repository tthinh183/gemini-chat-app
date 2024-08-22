package com.example.chat_with_gemini.helper.callback

interface ResponseCallback {
    fun onResponse(response: String)
    fun onError(throwable: Throwable)
}