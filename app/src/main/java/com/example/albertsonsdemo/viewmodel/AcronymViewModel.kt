package com.example.albertsonsdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albertsonsdemo.model.AcronymWrapper
import com.example.albertsonsdemo.repo.AcronymRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AcronymViewModel : ViewModel() {
    private val _acronym = MutableLiveData<AcronymWrapper>()

    val acronym: LiveData<AcronymWrapper>
        get() = _acronym

    fun fetchAcronym(shortForm: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val acronym = AcronymRepo.getLongForm(shortForm)
            _acronym.postValue(AcronymWrapper(acronym.code(), acronym.message(), acronym.body()))
        }
    }
}