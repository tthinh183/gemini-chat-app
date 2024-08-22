package com.example.chat_with_gemini.di

import com.example.chat_with_gemini.utils.GeminiPro
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGemini(): GeminiPro {
        return GeminiPro()
    }

}