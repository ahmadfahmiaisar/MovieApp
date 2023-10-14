package com.santridev.newmovieapp.base.exception

data class ClientErrorException(val code: Int) : Exception()

class EmptyResponseException : Exception()

class NoInternetConnection : Exception()

data class ServerErrorException(val code: Int) : Exception()

class TimeoutException : Exception()

data class UnknownNetworkErrorException(val errorMessage: String) : Exception()