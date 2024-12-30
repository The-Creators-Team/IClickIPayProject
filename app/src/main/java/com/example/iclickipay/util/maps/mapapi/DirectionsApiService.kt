package com.example.iclickipay.util.maps.mapapi

import com.example.iclickipay.util.maps.maputil.DirectionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionsApiService {
    @GET("directions/json")
    suspend fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") apiKey: String
    ): DirectionsResponse
}