package com.ronasit.account.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ronasit.account.model.HelpInfo

data class HelpPage (
    @SerializedName("name")
    @Expose()
    var name: String?,
    @SerializedName("content")
    @Expose()
    var content: List<HelpInfo>

)