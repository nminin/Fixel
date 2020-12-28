package com.nminin.bindingbuilder.default

import android.view.View
import com.nminin.bindingbuilder.BindingDecorator

class SelectedDecorator<V: View> : BindingDecorator<V, Boolean>() {
    override fun bind(view: V, item: Boolean) {
        view.isSelected = item
    }
}