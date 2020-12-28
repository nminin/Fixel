package com.nminin.bindingbuilder

import android.view.View

abstract class BindingDecorator<V: View, T>(
    private var actionsCallback: ((view: V, ActionId: Int) -> Unit)? = null
) {
    abstract fun bind(view: V, item: T)
}