package com.example.albertsonsdemo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcronymItem(
        val lfs: List<Lf>,
        val sf: String
)