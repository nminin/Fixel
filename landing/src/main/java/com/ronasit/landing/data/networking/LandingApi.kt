package com.ronasit.landing.data.networking

import com.ronasit.landing.data.networking.response.LandingResponse
import retrofit2.Call
import retrofit2.http.GET

internal interface LandingApi {

    @GET("/landing_page")
    fun getLanding(): Call<LandingResponse>
}