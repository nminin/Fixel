package com.ronasit.landing.data.networking.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ronasit.core.model.BrandsContent
import com.ronasit.core.model.BrandsHeader

data class BrandsResponse(
    @SerializedName("lifestyle_banner")
    @Expose
    var header: BrandsHeader,
    @SerializedName("top_brands")
    @Expose
    var content: BrandsContent

)