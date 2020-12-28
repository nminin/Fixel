package com.nminin.bindingbuilder.default

import android.view.View
import com.nminin.bindingbuilder.BindingDecorator

class VisibilityDecorator<V: View> : BindingDecorator<V, Int>() {
    override fun bind(view: V, item: Int) {
        view.visibility = item
    }
}