package com.ronasit.authorization.data.networking

import com.ronasit.authorization.data.networking.request.LoginRequest
import com.ronasit.authorization.data.networking.request.SignupRequest
import com.ronasit.authorization.data.networking.response.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationApi {

    @POST("/auth/sign-in")
    fun login(@Body() loginRequest: LoginRequest): Call<TokenResponse>

    @POST("/auth/sign-up")
    fun signUp(@Body() signupRequest: SignupRequest): Call<TokenResponse>
}