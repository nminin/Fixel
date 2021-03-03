package com.ronasit.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("first_name")
    @Expose
    var firstName: String? = null,
    @SerializedName("last_name")
    @Expose
    var lastName: String? = null,
    @SerializedName("gender")
    @Expose
    var gender: String? = null,
    @SerializedName("identification_number")
    @Expose
    var identificationNumber: String? = null,
    @SerializedName("email")
    @Expose
    var email: String? = null,
    @SerializedName("phone_number")
    @Expose
    var phoneNumber: String? = null
)