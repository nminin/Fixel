package com.ronasit.core.ui.adapter

import android.view.View
import com.nminin.bindingbuilder.recycler.ViewHolder
import com.ronasit.core.model.Style

abstract class StyledViewHolder<T>(view: View, protected val style: Style): ViewHolder<T>(view) {
}