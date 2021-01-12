package com.ronasit.core.ui.appbar

import com.ronasit.core.R

enum class AppBarStyle(
    val backgroundColor: Int,
    val tintColor: Int
) {
    TRANSPARENT(R.color.transparent, R.color.white),
    AUTHORIZATION(R.color.transparent, R.color.black),
    WHITE(R.color.white, R.color.black)
}