package com.ronasit.landing.data.networking.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ronasit.core.model.HighlightText

internal class LandingResponse {

    @SerializedName("categories")
    @Expose
    val categories: CategoriesResponse? = null
    @SerializedName("banner")
    @Expose
    val banner: Banner? = null


    class Banner {
        @SerializedName("background_image")
        @Expose
        val headerImage: String? = null
        @SerializedName("text_header")
        @Expose
        val textHeader: HighlightText? = null
        @SerializedName("text_main")
        @Expose
        val textMain: HighlightText? = null
        @SerializedName("text_promo")
        @Expose
        val textPromo: HighlightText? = null
        @SerializedName("text_bottom")
        @Expose
        val textBottom: HighlightText? = null
        @SerializedName("personalization_button")
        @Expose
        val button: Button? = null

    }

    class Button {
        @SerializedName("is_visible")
        @Expose
        var isVisible = false
        @SerializedName("text")
        @Expose
        var text = ""
    }

    class CategoriesResponse {
        @SerializedName("text_header")
        @Expose
        val textHeader: HighlightText? = null
        @SerializedName("items")
        @Expose
        val items: List<CategoryResponse>? = null


        class CategoryResponse() {
            @SerializedName("name")
            @Expose
            var name: String? = null
            @SerializedName("gender_id")
            @Expose
            var genderId: String? = null
            @Expose
            @SerializedName("background_image")
            var image: String? = null
        }
    }
}