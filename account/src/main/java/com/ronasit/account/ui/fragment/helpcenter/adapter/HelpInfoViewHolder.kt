package com.ronasit.account.ui.fragment.helpcenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ronasit.account.R
import com.ronasit.account.model.HelpInfo
import com.ronasit.core.model.Style
import com.ronasit.core.ui.adapter.StyledViewHolder

class HelpInfoViewHolder(view: View, style: Style) : StyledViewHolder<HelpInfo>(view, style) {

    companion object {
        fun create(parent: ViewGroup, layout: Int = R.layout.item_help, style: Style) =
            HelpInfoViewHolder(
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
        itemView.findViewById<TextView>(R.id.item_text_header).text = item.headerText
        itemView.findViewById<TextView>(R.id.item_text_preheader).text = item.preheaderText
        itemView.findViewById<TextView>(R.id.item_text_body).text = item.bodyText
    }
}