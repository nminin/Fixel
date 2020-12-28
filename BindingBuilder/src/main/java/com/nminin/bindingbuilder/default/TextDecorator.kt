package com.nminin.bindingbuilder.default

import android.widget.TextView
import com.nminin.bindingbuilder.BindingDecorator

class TextDecorator() : BindingDecorator<TextView, String>() {
    override fun bind(view: TextView, item: String) {
        if (view.text != item) {
            view.text = item
        }
    }

}