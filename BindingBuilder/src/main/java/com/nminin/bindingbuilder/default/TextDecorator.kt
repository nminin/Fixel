package com.nminin.bindingbuilder.default

import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.nminin.bindingbuilder.BindingDecorator

class TextDecorator<V: TextView>() : BindingDecorator<V, String>() {
    override fun bind(view: V, item: String) {
        if (!view.text.toString().equals(item)) {
            view.text = item
        }
    }

}

class ButtonTextDecorator() : BindingDecorator<Button, String>() {
    override fun bind(view: Button, item: String) {
        if (view.text != item) {
            view.text = item
        }
    }

}