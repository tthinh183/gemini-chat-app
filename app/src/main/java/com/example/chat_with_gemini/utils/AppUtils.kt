package com.example.chat_with_gemini.utils

import com.example.chat_with_gemini.helper.callback.ResponseCallback
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.BlockThreshold;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.ai.client.generativeai.type.HarmCategory;
import com.google.ai.client.generativeai.type.SafetySetting;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor

class GeminiPro {

    fun getResponse(query: String, callback: ResponseCallback) {
        val model = getModel()
        val content = Content.Builder().text(query).build()
        val executor = Executor { it.run() }

        val response: ListenableFuture<GenerateContentResponse> = model.generateContent(content)
        Futures.addCallback(response, object : FutureCallback<GenerateContentResponse> {
            override fun onSuccess(result: GenerateContentResponse) {
                val resultText = result.text
                callback.onResponse(resultText!!)
            }

            override fun onFailure(throwable: Throwable) {
                throwable.printStackTrace()
                callback.onError(throwable)
            }
        }, executor)
    }

    private fun getModel(): GenerativeModelFutures {
        val apiKey = Constant.API_KEY

        val harassmentSafety = SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.ONLY_HIGH)

        val configBuilder = GenerationConfig.Builder()
        configBuilder.temperature = 0.9f
        configBuilder.topK = 16
        configBuilder.topP = 0.1f
        val generationConfig = configBuilder.build()

        val gm = GenerativeModel(
            "gemini-pro",
            apiKey,
            generationConfig,
            listOf(harassmentSafety)
        )

        return GenerativeModelFutures.from(gm)
    }
}