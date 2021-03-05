package com.ronasit.account.networking

import com.ronasit.account.model.HelpPage
import com.ronasit.account.networking.model.ChangePasswordRequest
import com.ronasit.core.model.User
import retrofit2.Call
import retrofit2.http.*

interface AccountApi {
    @GET("/accounts/informations")
    fun getAccount(): Call<User>

    @POST("/accounts/change_password")
    fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Call<Unit>

    @GET("/static_page/get/{page}")
    fun getHelpInfo(@Path("page") path: String): Call<HelpPage>



}