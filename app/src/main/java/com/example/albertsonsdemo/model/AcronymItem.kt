package com.example.albertsonsdemo.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcronymItem(
        @field:Json(name = "lfs")
        val acronymMeanings: List<AcronymMeaning>,
        @field:Json(name = "sf")
        val acronym: String
)