package com.ronasit.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Button (
    @SerializedName("is_visible")
    @Expose
    var isVisible: Boolean = false,
    @SerializedName("name")
    @Expose
    var name: HighlightText?,
    @SerializedName("filter_values")
    @Expose
    var filter: Filter?
)