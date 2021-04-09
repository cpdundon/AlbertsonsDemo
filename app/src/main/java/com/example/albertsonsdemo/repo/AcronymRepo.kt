package com.example.albertsonsdemo.repo

import com.example.albertsonsdemo.model.AcronymItem
import com.example.albertsonsdemo.repo.remote.AcronymRetroInstance
import retrofit2.Response

object AcronymRepo {
    suspend fun getLongForm(
            shortForm: String,
    ): Response<List<AcronymItem>> {
        return AcronymRetroInstance.acronymService.getLongForm(shortForm)
    }
}

