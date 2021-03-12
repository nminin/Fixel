package com.ronasit.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BrandsContent(
    @SerializedName("title")
    @Expose
    var title: Title,
    @SerializedName("brands")
    @Expose
    var brands: List<Brand>,
    @SerializedName("button_all_brands")
    @Expose
    var buttonAllBrands: Button
)