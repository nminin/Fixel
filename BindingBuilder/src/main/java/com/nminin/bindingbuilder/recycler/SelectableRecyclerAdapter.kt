package com.nminin.bindingbuilder.recycler

import android.view.ViewGroup

class SelectableRecyclerAdapter<T, V : SelectableViewHolder<T>>(
    private val viewHolderFactory: ViewHolderFactory<T,V>
) : RecyclerAdapter<T, V>(viewHolderFactory) {
    private var selectedItems: List<T> = emptyList()

    override fun onBindViewHolder(holder: V, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.isSelected(items.get(position), selectedItems)
    }

    fun setSelectedItems(items: List<T>) {
        selectedItems = items
        notifyDataSetChanged()
    }
}