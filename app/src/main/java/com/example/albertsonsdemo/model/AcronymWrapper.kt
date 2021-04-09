package com.example.albertsonsdemo.model

data class AcronymWrapper(val httpCode: Int,
                          val message: String,
                          val acronym: List<AcronymItem>?
)