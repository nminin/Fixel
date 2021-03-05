package com.ronasit.account.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HelpInfo (
    @SerializedName("section_name")
    @Expose
    var sectionName: String?,
    @SerializedName("body_text")
    @Expose
    var bodyText: String = "",
    @SerializedName("section_position")
    @Expose
    var position: Int = Int.MAX_VALUE,
    @SerializedName("header_text")
    @Expose
    var headerText: String = "",
    @SerializedName("preheader_text")
    @Expose
    var preheaderText: String = "",
    @SerializedName("is_enabled")
    @Expose
    var isEnabled: Boolean
)