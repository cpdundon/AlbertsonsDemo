package com.example.albertsonsdemo.repo.remote

import com.example.albertsonsdemo.model.AcronymItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {
    @GET("dictionary.py")
    suspend fun getLongForm(@Query("sf") shortForm: String): Response<List<AcronymItem>>
}