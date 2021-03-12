package com.ronasit.landing.ui.landing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nminin.bindingbuilder.recycler.ViewHolder
import com.ronasit.core.model.Category
import com.ronasit.landing.R

class CategoryViewHolder private constructor(view: View) : ViewHolder<Category>(view) {

    companion object {
        fun create(parent: ViewGroup, layout: Int = R.layout.item_gender) =
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
        Glide.with(itemView)
            .load(item.imageUrl)
            .into(itemView.findViewById(R.id.item_image))
        itemView.findViewById<TextView>(R.id.item_text).text = item.name
    }
}