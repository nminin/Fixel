package com.ronasit.account.networking

import com.ronasit.core.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface AccountApi {
    @GET("/accounts/informations")
    fun getAccount(): Call<User>

    @POST("/accounts/change_password")
    fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Call<Unit>

}