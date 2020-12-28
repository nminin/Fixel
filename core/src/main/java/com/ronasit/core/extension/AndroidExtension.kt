package com.ronasit.core.extension

import android.view.View

fun Boolean.toVisibility(falseValue: Int = View.GONE) = if (this) {
    View.VISIBLE
} else {
    falseValue
}