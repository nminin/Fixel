package com.ronasit.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class HighlightText {
    @SerializedName("template")
    @Expose
    var text: String? = null
    @SerializedName("highlights")
    @Expose
    var highlights: List<String> = emptyList()
}