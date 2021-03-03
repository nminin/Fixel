package com.ronasit.account.networking

import com.ronasit.core.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT

interface AccountApi {
    @GET("/accounts/informations")
    fun getAccount(): Call<User>

}