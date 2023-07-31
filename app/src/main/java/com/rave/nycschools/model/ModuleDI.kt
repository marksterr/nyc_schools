package com.rave.nycschools.model

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rave.nycschools.model.remote.SchoolService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create

/**
 * Dagger Hilt module for dependency injection.
 */
@Module
@InstallIn(SingletonComponent::class)
object ModuleDI {
    private const val BASE_URL = "https://data.cityofnewyork.us/resource/"

    // Define the media type for JSON
    private val mediaType: MediaType = MediaType.parse("application/json")!!

    /**
     * Provides a [Json] instance for JSON serialization/deserialization.
     */
    @OptIn(ExperimentalSerializationApi::class)
    private fun providesJson(): Json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    /**
     * Provides a [Retrofit] instance for network operations.
     */
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(providesJson().asConverterFactory(mediaType))
        .build()

    /**
     * Provides an instance of [SchoolService].
     *
     * @param retrofit The Retrofit instance.
     * @return The SchoolService instance.
     */
    @Provides
    fun getAPIService(retrofit: Retrofit): SchoolService = retrofit.create()
}