package com.nminin.bindingbuilder.recycler

import android.view.View

abstract class SelectableViewHolder<T>(view: View) : ViewHolder<T>(view) {

    abstract fun isSelected(item: T, selectedItems: List<T>)
}