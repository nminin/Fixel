package com.ronasit.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("image_url")
    @Expose
    var image: String?,
    @SerializedName("logo_url")
    @Expose
    var logo: String?,
    @SerializedName("filter_values")
    @Expose
    var filter: Filter?
)