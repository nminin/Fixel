package com.ronasit.core.base.binding

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nminin.bindingbuilder.BindingDecorator

class EnabledDecorator <V: View>: BindingDecorator<V, Boolean>() {
    override fun bind(view: V, item: Boolean) {
        view.isEnabled = item
    }
}