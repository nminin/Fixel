package com.ronasit.account.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ronasit.core.model.HighlightText

data class HelpInfo(
    @SerializedName("section_name")
    @Expose
    var sectionName: String?,
    @SerializedName("body_text")
    @Expose
    var bodyText: String? = null,
    @SerializedName("section_position")
    @Expose
    var position: Int = Int.MAX_VALUE,
    @SerializedName("header_text")
    @Expose
    var headerText: String? = null,
    @SerializedName("preheader_text")
    @Expose
    var preheaderText: String? = null,
    @SerializedName("background_image")
    @Expose
    var backgroundImage: String? = null,
    @SerializedName("button_text")
    @Expose
    var buttonText: String? = null,
    @SerializedName("header")
    @Expose
    var headerHighlightText: HighlightText? = null,
    @SerializedName("logo")
    @Expose
    var logo: List<String>? = null,
    @SerializedName("image")
    @Expose
    var images: List<String>? = null,
    @SerializedName("is_enabled")
    @Expose
    var isEnabled: Boolean
)