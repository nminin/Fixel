package com.ronasit.account.ui.fragment.helpcenter.adapter

import android.util.Log
import android.view.ViewGroup
import com.ronasit.account.model.HelpInfo
import com.ronasit.core.model.Style
import com.ronasit.core.ui.adapter.StyledViewHolder
import com.ronasit.core.ui.adapter.StyledViewHolderFactory

class HelpInfoViewHolderFactory(
    style: Style,
    private val onButtonClick: (String) -> Unit
) :
    StyledViewHolderFactory<HelpInfo, StyledViewHolder<HelpInfo>>(style) {

    override fun getViewType(item: HelpInfo): Int = item.getType().also {
        Log.d("asdasdasd", it.name)
    }.id

    override fun getViewHolder(viewType: Int, viewGroup: ViewGroup): StyledViewHolder<HelpInfo> =
        when (viewType) {
            HelpInfo.Type.BACKGROUND_IMAGE_COLORED.id ->
                HelpInfoBackgroundImageColoredViewHolder.create(
                    viewGroup,
                    style = this.style
                )
            HelpInfo.Type.BACKGROUND_IMAGE.id -> HelpInfoBackgroundImageViewHolder.create(
                viewGroup,
                style = this.style
            )
            HelpInfo.Type.REGULAR.id -> HelpInfoViewHolder.create(viewGroup, style = this.style)
            HelpInfo.Type.BUTTON.id -> HelpInfoButtonViewHolder.create(viewGroup, style = this.style) {
                onButtonClick.invoke(it)
            }
            HelpInfo.Type.LOGO.id -> HelpInfoLogoViewHolder.create(viewGroup, style = this.style)
            HelpInfo.Type.IMAGES.id -> HelpInfoImageViewHolder.create(viewGroup, style = this.style)
            HelpInfo.Type.EXPANDABLE.id -> HelpInfoExpandableViewHolder.create(viewGroup, style = this.style)
            else -> HelpInfoExpandableViewHolder.create(viewGroup, style = this.style)
        }

}