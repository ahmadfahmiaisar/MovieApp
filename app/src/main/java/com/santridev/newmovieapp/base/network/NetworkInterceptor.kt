package com.santridev.newmovieapp.base.network

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMjUwY2QyMDJhOThlNDBmZGFlOTQzNjg2OGQ1ZmVmNSIsInN1YiI6IjViOWEzMWVmYzNhMzY4Mjc5NTAwNmZmMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.fgMTL3-NT8v_f4Iq3D9l8Of4voU0LaUqLicMmWX-jIg"
        val newRequest = chain
            .request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        return chain.proceed(newRequest)
    }

}