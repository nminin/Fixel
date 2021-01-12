package com.ronasit.authorization.data.networking.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SocialSignupRequest (
    @SerializedName("access_token")
    @Expose
    val accessToken: String? = null
)