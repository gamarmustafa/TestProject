package com.gamar.testproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamar.testproject.model.PhoneModel
import com.gamar.testproject.remote.Repository
import com.gamar.testproject.remote.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private var _phoneList = MutableStateFlow<List<PhoneModel>?>(null)
    val phoneList = _phoneList.asStateFlow()

    init {
        getPhoneModels()
    }

     private fun getPhoneModels() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPhones()
            when (result) {
                is ResultWrapper.Success -> {
                    _phoneList.value = result.value
                }

                else -> _phoneList.value = emptyList()

            }

        }
    }


}