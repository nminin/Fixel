package com.ronasit.core.ui.adapter

import com.nminin.bindingbuilder.recycler.ViewHolderFactory
import com.ronasit.core.model.Style

abstract class StyledViewHolderFactory<T, V: StyledViewHolder<T>>(
    protected val style: Style
): ViewHolderFactory<T,V>() {

}