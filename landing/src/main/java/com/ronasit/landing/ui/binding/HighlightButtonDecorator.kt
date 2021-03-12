package com.ronasit.landing.ui.binding

import androidx.appcompat.widget.AppCompatButton
import com.nminin.bindingbuilder.BindingDecorator
import com.ronasit.core.extension.toVisibility
import com.ronasit.core.model.Button

class HighlightButtonDecorator : BindingDecorator<AppCompatButton, Button>() {
    override fun bind(view: AppCompatButton, item: Button) {
        var text = item.name?.text ?: ""
        item.name?.highlights?.forEach {
            text = text.replace("%@", it)
        }
        view.text = text
        view.visibility = item.isVisible.toVisibility()
    }
}