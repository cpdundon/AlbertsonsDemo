package com.example.albertsonsdemo.repo.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AcronymRetroInstance {
    private const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"

    private val client = HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            .let { loginInterceptor ->
                OkHttpClient.Builder().addInterceptor(loginInterceptor).build()
            }

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

    val acronymService: AcronymService by lazy { retrofit.create(AcronymService::class.java) }
}