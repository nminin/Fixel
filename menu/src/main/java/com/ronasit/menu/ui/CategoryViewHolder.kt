package com.ronasit.menu.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nminin.bindingbuilder.recycler.ViewHolder
import com.ronasit.core.model.Category
import com.ronasit.menu.R

class CategoryViewHolder(view: View): ViewHolder<Category>(view) {

    companion object {
        fun create(parent: ViewGroup, layout: Int = R.layout.item_category) =
            CategoryViewHolder(
                LayoutInflater.from(parent.context).inflate(layout, parent, false)
            )
    }
    override fun bind(
        item: Category,
        editMode: Boolean,
        onItemClick: ((Category) -> Unit)?,
        onLongItemClick: ((Category) -> Unit)?,
        additionalEvents: List<Pair<Int, (Category) -> Unit>>
    ) {
        super.bind(item, editMode, onItemClick, onLongItemClick, additionalEvents)
        itemView.findViewById<TextView>(R.id.item_text).text = item.name
    }
}