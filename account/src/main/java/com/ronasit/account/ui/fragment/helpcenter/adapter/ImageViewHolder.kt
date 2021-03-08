package com.ronasit.account.ui.fragment.helpcenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nminin.bindingbuilder.recycler.ViewHolder
import com.ronasit.account.R

class ImageViewHolder(view: View) : ViewHolder<String>(view) {
    companion object {
        fun create(parent: ViewGroup, layout: Int = R.layout.item_logo) =
            ImageViewHolder(
                LayoutInflater.from(parent.context).inflate(layout, parent, false)
            )
    }

    override fun bind(
        item: String,
        editMode: Boolean,
        onItemClick: ((String) -> Unit)?,
        onLongItemClick: ((String) -> Unit)?,
        additionalEvents: List<Pair<Int, (String) -> Unit>>
    ) {
        super.bind(item, editMode, onItemClick, onLongItemClick, additionalEvents)
        with(itemView.findViewById<ImageView>(R.id.item_image)) {
            Glide.with(this)
                .load(item)
                .into(this)
        }
    }
}