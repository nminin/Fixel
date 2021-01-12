package com.ronasit.authorization.data.networking.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("email")
    @Expose
    val email: String? = null,
    @SerializedName("password")
    @Expose
    val password: String? = null,
    @SerializedName("identification_number")
    @Expose
    val identificationNumber: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("phone_number")
    @Expose
    val phoneNumber: String? = null
)