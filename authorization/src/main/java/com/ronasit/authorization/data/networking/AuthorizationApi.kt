package com.ronasit.authorization.data.networking

import com.ronasit.authorization.data.networking.request.LoginRequest
import com.ronasit.authorization.data.networking.request.SignupRequest
import com.ronasit.authorization.data.networking.response.TokenResponse
import retrofit2.Call
import retrofit2.http.GET

interface AuthorizationApi {

    @GET("/landing_page")
    fun login(loginRequest: LoginRequest): Call<TokenResponse>

    @GET("/auth/sign-up")
    fun signUp(signupRequest: SignupRequest): Call<TokenResponse>
}