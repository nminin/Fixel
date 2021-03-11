package com.ronasit.account.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ronasit.core.model.User

data class UpdateAccountInfo(
    @SerializedName("first_name")
    @Expose
    var name: String,
    @SerializedName("last_name")
    @Expose
    var lastName: String,
    @SerializedName("email")
    @Expose
    var email: String,
    @SerializedName("phone_number")
    @Expose
    var phoneNumber: String?,
    @SerializedName("gender")
    @Expose
    var gender: User.Gender?,
    @SerializedName("identification_number")
    @Expose
    var identificationNumber: String?
) {

    companion object {
        fun fromUser(item: User) = UpdateAccountInfo(
            item.firstName!!,
            item.lastName!!,
            item.email!!,
            item.phoneNumber,
            item.gender,
            item.identificationNumber
        )
    }
}