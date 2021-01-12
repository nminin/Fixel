package com.ronasit.authorization.data.networking.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    @Expose
    val email: String? = null,
    @SerializedName("password")
    @Expose
    val password: String? = null
)