package com.ronasit.account.ui.fragment.decorator

import android.widget.CompoundButton
import com.nminin.bindingbuilder.BindingDecorator

class GenderSwitchDecorator<V : CompoundButton> : BindingDecorator<V, Boolean>() {
    override fun bind(view: V, item: Boolean) {
        if (view.isChecked != item) {
            view.isChecked = item
        }
        view.isClickable = !view.isChecked
    }
}