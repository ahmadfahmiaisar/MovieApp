package com.santridev.newmovieapp.base.apiclient

import com.santridev.newmovieapp.base.exception.ClientErrorException
import com.santridev.newmovieapp.base.exception.EmptyResponseException
import com.santridev.newmovieapp.base.exception.NoInternetConnection
import com.santridev.newmovieapp.base.exception.ServerErrorException
import com.santridev.newmovieapp.base.exception.TimeoutException
import com.santridev.newmovieapp.base.exception.UnknownNetworkErrorException
import com.santridev.newmovieapp.base.state.ApiResponse
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response

@Singleton
class ApiClientImpl @Inject constructor() : ApiClient {
    override suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<Exception, T> {
        return try {
            val response = apiCall()
            when {
                response.isSuccessful && response.body() == null -> ApiResponse.Failure(
                    EmptyResponseException()
                )
                response.isSuccessful && response.body() != null -> ApiResponse.Success(response.body()!!)
                response.code() in 400..499 -> ApiResponse.Failure(ClientErrorException(response.code()))
                response.code() in 500..599 -> ApiResponse.Failure(ServerErrorException(response.code()))
                else -> ApiResponse.Failure(
                    UnknownNetworkErrorException(
                        response.errorBody().toString()
                    )
                )
            }
        } catch (exception: Exception) {
            handleError(exception)
        }
    }

    private fun <T> handleError(exception: Exception): ApiResponse<Exception, T> {
        return when (exception) {
            is UnknownHostException -> ApiResponse.Failure(NoInternetConnection())
            is SocketTimeoutException -> ApiResponse.Failure(TimeoutException())
            is IOException -> ApiResponse.Failure(
                UnknownNetworkErrorException(
                    exception.message ?: "Unknown IO Exception error"
                )
            )
            else -> ApiResponse.Failure(
                UnknownNetworkErrorException(
                    exception.localizedMessage ?: "Unknown error"
                )
            )
        }
    }
}