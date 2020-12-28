package com.nminin.bindingbuilder.default

import android.widget.CompoundButton
import com.nminin.bindingbuilder.BindingDecorator

class CheckedDecorator<V : CompoundButton> : BindingDecorator<V, Boolean>() {
    override fun bind(view: V, item: Boolean) {
        if (view.isChecked != item) {
            view.isChecked = item
        }
    }
}