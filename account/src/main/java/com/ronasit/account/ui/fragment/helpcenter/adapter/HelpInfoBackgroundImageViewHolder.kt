package com.ronasit.account.ui.fragment.helpcenter.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ronasit.account.R
import com.ronasit.account.model.HelpInfo
import com.ronasit.core.extension.setHighlightText
import com.ronasit.core.model.Style
import com.ronasit.core.ui.adapter.StyledViewHolder

class HelpInfoBackgroundImageViewHolder(view: View, style: Style): StyledViewHolder<HelpInfo>(view, style) {
    private var isVisible = false

    companion object {
        fun create(parent: ViewGroup, layout: Int = R.layout.item_help_background_image, style: Style) =
            HelpInfoBackgroundImageViewHolder(
                LayoutInflater.from(parent.context).inflate(layout, parent, false),
                style
            )
    }

    override fun bind(
        item: HelpInfo,
        editMode: Boolean,
        onItemClick: ((HelpInfo) -> Unit)?,
        onLongItemClick: ((HelpInfo) -> Unit)?,
        additionalEvents: List<Pair<Int, (HelpInfo) -> Unit>>
    ) {
        super.bind(item, editMode, onItemClick, onLongItemClick, additionalEvents)
        if (isVisible) {
            itemView.visibility = View.VISIBLE
        } else {
            itemView.visibility = View.GONE
        }
        with(itemView.findViewById<TextView>(R.id.item_text_header)) {
            item.headerHighlightText?.let {
                this.setHighlightText(
                    it,
                    style,
                    "#FFFFFF"
                )
            } ?: run {
                this.text = item.headerText
            }
        }

        with(itemView.findViewById<ImageView>(R.id.item_image_background)) {
            item.backgroundImage?.let {
                Glide.with(this)
                    .load(it)
                    .addListener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            isVisible = true
                            itemView.visibility = View.VISIBLE
                            return false
                        }

                    })
                    .into(this)
            }
        }

        itemView.findViewById<TextView>(R.id.item_text_preheader).text = item.preheaderText
        itemView.findViewById<TextView>(R.id.item_text_text).text = item.bodyText
    }
}