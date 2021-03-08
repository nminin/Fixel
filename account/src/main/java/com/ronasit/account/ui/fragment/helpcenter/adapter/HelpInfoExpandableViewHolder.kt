package com.ronasit.account.ui.fragment.helpcenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ronasit.account.R
import com.ronasit.account.model.HelpInfo
import com.ronasit.core.extension.toVisibility
import com.ronasit.core.model.Style
import com.ronasit.core.ui.adapter.StyledViewHolder

class HelpInfoExpandableViewHolder(view: View, style: Style) : StyledViewHolder<HelpInfo>(view, style) {
    private var isVisible = false

    companion object {
        fun create(parent: ViewGroup, layout: Int = R.layout.item_help_expandable, style: Style) =
            HelpInfoExpandableViewHolder(
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
        itemView.findViewById<TextView>(R.id.item_text_header)
            .text = item.headerText
        itemView.findViewById<LinearLayout>(R.id.item_layout_header)
            .setOnClickListener {
                isVisible = !isVisible
                itemView.findViewById<ImageView>(R.id.item_image_header).setImageResource(
                    if (isVisible) {
                        R.drawable.ic_close
                    } else {
                        R.drawable.ic_open
                    }
                )
                itemView.findViewById<TextView>(R.id.item_text_body).visibility =
                    isVisible.toVisibility()
                itemView.findViewById<View>(R.id.item_underline_body).visibility =
                    isVisible.toVisibility()
            }

        itemView.findViewById<ImageView>(R.id.item_image_header).setImageResource(
            if (isVisible) {
                R.drawable.ic_close
            } else {
                R.drawable.ic_open
            }
        )
        itemView.findViewById<TextView>(R.id.item_text_body).visibility = isVisible.toVisibility()
        itemView.findViewById<View>(R.id.item_underline_body).visibility = isVisible.toVisibility()
        itemView.findViewById<TextView>(R.id.item_text_body).text = item.bodyText


    }
}