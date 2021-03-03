package com.ronasit.authorization.data.networking.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthorizationError {
    @SerializedName("Email")
    @Expose
    var email: String? = ""
    @SerializedName("Password")
    @Expose
    var password: String? = ""
    @SerializedName("Message")
    @Expose
    var message: String? = ""

}