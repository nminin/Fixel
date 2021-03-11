package com.ronasit.account.ui.fragment.helpcenter.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.ronasit.account.R
import com.ronasit.account.model.HelpInfo
import com.ronasit.core.model.Style
import com.ronasit.core.ui.adapter.StyledViewHolder

class HelpInfoButtonViewHolder(
    view: View,
    style: Style,
    private val onButtonClick: (String) -> Unit
) :
    StyledViewHolder<HelpInfo>(view, style) {

    companion object {
        fun create(
            parent: ViewGroup,
            layout: Int = R.layout.item_help_button,
            style: Style,
            onButtonClick: (String) -> Unit
        ) =
            HelpInfoButtonViewHolder(
                LayoutInflater.from(parent.context).inflate(layout, parent, false),
                style,
                onButtonClick
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
        with(itemView.findViewById<TextView>(R.id.item_button)) {
            text = item.buttonText
            this.setOnClickListener {
                onButtonClick.invoke(item.sectionName ?: "")
            }
            this.backgroundTintList = ContextCompat.getColorStateList(
                this.context,
                style.buttonTintColor
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.compoundDrawableTintList = ContextCompat.getColorStateList(
                    this.context,
                    style.buttonTextColor
                )
            }
            this.setTextColor(
                ContextCompat.getColorStateList(
                    this.context,
                    style.buttonTextColor
                )
            )
        }
    }
}