
package com.example.albertsonsdemo.utils

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val errorMsg: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}