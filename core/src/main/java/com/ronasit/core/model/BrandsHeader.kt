package com.ronasit.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BrandsHeader(
    @SerializedName("scenario")
    @Expose
    var scenario: String?,
    @SerializedName("background_image")
    @Expose
    var backgroundImage: String?,
    @SerializedName("title")
    @Expose
    var title: Title?,
    @SerializedName("subtitle")
    @Expose
    var subtitle: Title?,
    @SerializedName("button_special_products")
    @Expose
    var buttonSpecialProducts: Button?,
    @SerializedName("button_all_products")
    @Expose
    var buttonAllProducts: Button?
)