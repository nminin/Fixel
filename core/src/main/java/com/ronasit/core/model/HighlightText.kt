package com.ronasit.core.model

import android.content.Context
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class HighlightText {
    @SerializedName("template")
    @Expose
    var text: String? = null

    @SerializedName("highlights")
    @Expose
    var highlights: List<String> = emptyList()

    companion object {
        fun fromRes(context: Context, textId: Int, highlights: List<Int>) = HighlightText().apply {
            this.text = context.getString(textId)
            this.highlights = highlights.map {
                context.getString(it)
            }
        }
        fun fromText(text: String, highlights: List<String>) = HighlightText().apply {
            this.text = text
            this.highlights = highlights
        }

    }
}