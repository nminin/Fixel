package com.ronasit.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Filter (
    @SerializedName("brand")
    @Expose
    var brand: List<String>,
    @SerializedName("gender")
    @Expose
    var gender: List<String>,
    @SerializedName("size")
    @Expose
    var size: List<String>
)