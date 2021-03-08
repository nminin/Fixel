package com.ronasit.account.ui.fragment.helpcenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nminin.bindingbuilder.recycler.RecyclerAdapter
import com.nminin.bindingbuilder.recycler.ViewHolderFactory
import com.ronasit.account.R
import com.ronasit.account.model.HelpInfo
import com.ronasit.core.model.Style
import com.ronasit.core.ui.adapter.StyledViewHolder

class HelpInfoLogoViewHolder(view: View, style: Style) : StyledViewHolder<HelpInfo>(view, style) {

    private val adapter = RecyclerAdapter(object : ViewHolderFactory<String, ImageViewHolder>() {
        override fun getViewType(item: String): Int = 1

        override fun getViewHolder(viewType: Int, viewGroup: ViewGroup): ImageViewHolder =
            ImageViewHolder.create(viewGroup)
    })

    companion object {
        fun create(parent: ViewGroup, layout: Int = R.layout.item_help_images, style: Style) =
            HelpInfoLogoViewHolder(
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
        with(itemView.findViewById<RecyclerView>(R.id.item_recycler)) {
            this.layoutManager =
                GridLayoutManager(
                    itemView.context,
                    2,
                    GridLayoutManager.VERTICAL,
                    false
                )
            this.adapter = this@HelpInfoLogoViewHolder.adapter
        }
        item.logo?.let {
            adapter.updateData(it)
        }
    }
}