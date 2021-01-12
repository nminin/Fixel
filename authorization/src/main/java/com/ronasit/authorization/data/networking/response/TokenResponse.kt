package com.ronasit.authorization.data.networking.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TokenResponse {
    @SerializedName("auth_token")
    @Expose
    var token: String? = null
}