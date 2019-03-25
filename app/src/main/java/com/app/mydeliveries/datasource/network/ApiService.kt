package com.app.mydeliveries.datasource.network

import com.app.mydeliveries.datasource.model.Delivery
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/deliveries")
    fun getAllDeliveries(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<List<Delivery>>
}