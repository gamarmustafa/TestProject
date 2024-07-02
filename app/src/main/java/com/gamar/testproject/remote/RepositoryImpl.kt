package com.gamar.testproject.remote

import com.gamar.testproject.model.PhoneModel
import com.gamar.testproject.remote.utils.Constants
import com.gamar.testproject.remote.utils.ResultWrapper
import com.gamar.testproject.remote.utils.SafeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiClient: HttpClient) : Repository,
    SafeApiCall {


    override suspend fun getPhones(): ResultWrapper<List<PhoneModel>?> {

        return safeApiCall {
            val apiCall = apiClient.get {
                url(Constants.URL)
                contentType(ContentType.Application.Json)
            }

            return@safeApiCall when (apiCall.status.value) {
                HttpStatusCode.OK.value,
                HttpStatusCode.Created.value,
                HttpStatusCode.Accepted.value,
                HttpStatusCode.NoContent.value -> {
                    apiCall.body()
                }

                else -> {
                    null
                }
            }

        }
    }

    override suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                val result = apiCall.invoke()
                ResultWrapper.Success(result)
            } catch (e: Exception) {
                ResultWrapper.Error(e)
            }
        }

    }
}