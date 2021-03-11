package com.ronasit.style

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nminin.bindingbuilder.recycler.SelectableViewHolder
import com.ronasit.core.model.Style

class StyleViewHolder(view: View) : SelectableViewHolder<Style>(view) {

    companion object {
        fun create(parent: ViewGroup, layout: Int = R.layout.item_style) =
            StyleViewHolder(
                LayoutInflater.from(parent.context).inflate(layout, parent, false)
            )
    }

    override fun bind(
        item: Style,
        editMode: Boolean,
        onItemClick: ((Style) -> Unit)?,
        onLongItemClick: ((Style) -> Unit)?,
        additionalEvents: List<Pair<Int, (Style) -> Unit>>
    ) {
        super.bind(item, editMode, onItemClick, onLongItemClick, additionalEvents)
        with(itemView.findViewById<TextView>(R.id.item_style)) {
            text = item.name
        }
    }

    override fun isSelected(item: Style, selectedItems: List<Style>) {
        with(itemView.findViewById<TextView>(R.id.item_style)) {
            if (selectedItems.contains(item)) {
                setTextColor(resources.getColor(item.buttonTintColor))
            } else {
                setTextColor(resources.getColor(R.color.white))
            }
        }
    }
}