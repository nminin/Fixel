package com.ronasit.core.model

import androidx.annotation.ColorRes
import com.ronasit.core.R

enum class Style private constructor(
    val hilightColor: String,
    val textColor: String,
    val buttonTintColor: Int,
    val buttonTextColor: Int,
    val iconTint: Int,
    val switchTint: Int
) {

    YELLOW(
        "#FAC91F",
        "#FFFFFF",
        R.color.button_background_yelow,
        R.color.button_text_black,
        R.color.icon_tint_yelow,
        R.color.track_tint_yelow
    ),
    BLUE(
        "#2E7EFF",
        "#FFFFFF",
        R.color.button_background_blue,
        R.color.button_text_white,
        R.color.icon_tint_blue,
        R.color.track_tint_blue
    ),
    PINK(
        "#EA379E",
        "#FFFFFF",
        R.color.button_background_pink,
        R.color.button_text_white,
        R.color.icon_tint_pink,
        R.color.track_tint_pink
    );

    companion object {
        fun getAll() = listOf<Style>(
            YELLOW,
            BLUE,
            PINK
        )
    }
}