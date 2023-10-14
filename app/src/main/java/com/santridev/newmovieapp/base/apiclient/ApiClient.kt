package com.santridev.newmovieapp.base.apiclient

import com.santridev.newmovieapp.base.state.ApiResponse
import retrofit2.Response

interface ApiClient {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<Exception, T>
}