package com.gamar.testproject.di

import android.util.Log
import com.gamar.testproject.remote.Repository
import com.gamar.testproject.remote.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getKtorClient(): HttpClient {
        return HttpClient(Android){
            install(Logging){
                logger = CustomHttpLogger
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true

                    }
                )
            }
        }
    }

    @Provides
    @Singleton
    fun bindRepository(repositoryImpl: RepositoryImpl):Repository = repositoryImpl
}
private object CustomHttpLogger : Logger {
    private const val logTag = "CustomHttpLogger"

    override fun log(message: String) {
        Log.i(logTag, message)
    }
}
