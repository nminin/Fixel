package com.ronasit.landing.ui.brands

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nminin.bindingbuilder.recycler.ViewHolder
import com.ronasit.core.model.Brand
import com.ronasit.landing.R

class BrandViewHolder private constructor(view: View) : ViewHolder<Brand>(view) {

    companion object {
        fun create(parent: ViewGroup, layout: Int = R.layout.item_brand) =
            BrandViewHolder(
                LayoutInflater.from(parent.context).inflate(layout, parent, false)
            )
    }
    override fun bind(
        item: Brand,
        editMode: Boolean,
        onItemClick: ((Brand) -> Unit)?,
        onLongItemClick: ((Brand) -> Unit)?,
        additionalEvents: List<Pair<Int, (Brand) -> Unit>>
    ) {
        super.bind(item, editMode, onItemClick, onLongItemClick, additionalEvents)
        Glide.with(itemView)
            .load(item.logo)
            .circleCrop()
            .centerInside()
            .into(itemView.findViewById(R.id.item_image))
    }
}