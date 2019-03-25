package com.app.mydeliveries.datasource.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private var apiService: ApiService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://mock-api-mobile.dev.lalamove.com"
        private val apiClient: ApiClient = ApiClient()
        @Synchronized
        fun getInstance(): ApiClient {
            return apiClient
        }
    }
}