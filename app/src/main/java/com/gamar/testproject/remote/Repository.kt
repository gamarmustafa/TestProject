package com.gamar.testproject.remote

import com.gamar.testproject.model.PhoneModel
import com.gamar.testproject.remote.utils.ResultWrapper

interface Repository {

    suspend fun getPhones():ResultWrapper<List<PhoneModel>?>

}