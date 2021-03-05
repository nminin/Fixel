package com.ronasit.account.networking.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest (
    @SerializedName("old_password")
    @Expose
    val oldPassword: String,
    @SerializedName("new_password")
    @Expose
    val newPassword: String
)