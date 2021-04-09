package com.example.albertsonsdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.albertsonsdemo.R
import com.example.albertsonsdemo.model.AcronymMeaning
import com.example.albertsonsdemo.repo.AcronymRepo
import com.example.albertsonsdemo.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class AcronymViewModel(private val app: Application) : AndroidViewModel(app) {
    private val _dataState = MutableLiveData<DataState<List<AcronymMeaning>>>()

    val dataState: LiveData<DataState<List<AcronymMeaning>>>
        get() = _dataState

    private var currentQuery: String? = null

    private val emptyMsg
        get() = currentQuery?.let {
            String.format(app.getString(R.string.fancy_empty_result), currentQuery)
        } ?: app.getString(R.string.default_empty_results)

    private val <T>Response<T>.fancyErrorMsg
        get() = String.format(app.getString(R.string.rest_error), code(), message())

    fun fetchAcronym(shortForm: String) {
        currentQuery = shortForm
        viewModelScope.launch(Dispatchers.IO) {
            _dataState.postValue(DataState.Loading)
            AcronymRepo.getLongForm(shortForm).run {
                when (code()) {
                    200 -> {
                        body().run {
                            if (isNullOrEmpty()) DataState.Error(emptyMsg)
                            else DataState.Success(first().acronymMeanings)
                        }
                    }
                    else -> DataState.Error(fancyErrorMsg)
                }.let { _dataState.postValue(it) }
            }
        }
    }
}