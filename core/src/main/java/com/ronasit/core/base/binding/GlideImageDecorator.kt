package com.ronasit.core.base.binding

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nminin.bindingbuilder.BindingDecorator

class GlideImageDecorator<V: ImageView>: BindingDecorator<V, String?>() {
    override fun bind(view: V, item: String?) {
        item?.let {
            Glide.with(view)
                .load(it)
                .into(view)
        }
    }
}