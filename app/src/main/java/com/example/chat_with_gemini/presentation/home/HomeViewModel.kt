package com.example.chat_with_gemini.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chat_with_gemini.helper.callback.ResponseCallback
import com.example.chat_with_gemini.model.GeminiPro
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val geminiPro: GeminiPro
): ViewModel() {
    private val _response = mutableStateOf("")
    val response: State<String> = _response

    private val _error = mutableStateOf("")
    val error: State<String> = _error

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun getResponse(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            geminiPro.getResponse(query = query, object : ResponseCallback {
                override fun onResponse(response: String) {
                    _response.value = response
                    _isLoading.value = false
                }

                override fun onError(throwable: Throwable) {
                    _error.value = throwable.message.toString()
                    _isLoading.value = false
                }
            })
        }
    }
}