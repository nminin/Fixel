package com.ronasit.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Title (
    @SerializedName("is_visible")
    @Expose
    var isVisible: Boolean = false,
    @SerializedName("content")
    @Expose
    var content: HighlightText?
)