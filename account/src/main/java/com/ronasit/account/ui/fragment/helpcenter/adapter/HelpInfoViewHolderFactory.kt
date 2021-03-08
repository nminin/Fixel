package com.ronasit.account.ui.fragment.helpcenter.adapter

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
    private val EXPANDABLE = 0
    private val BACKGROUND_IMAGE = 1
    private val BACKGROUND_IMAGE_COLORED = 2
    private val REGULAR = 3
    private val BUTTON = 4
    private val LOGO = 5
    private val IMAGES = 6

    override fun getViewType(item: HelpInfo): Int = when {
        !item.backgroundImage.isNullOrEmpty() && item.headerHighlightText != null -> BACKGROUND_IMAGE_COLORED
        !item.backgroundImage.isNullOrEmpty() && !item.headerText.isNullOrEmpty() -> BACKGROUND_IMAGE
        !item.headerText.isNullOrEmpty() && !item.preheaderText.isNullOrEmpty() -> REGULAR
        !item.buttonText.isNullOrEmpty() -> BUTTON
        !item.logo.isNullOrEmpty() -> LOGO
        !item.images.isNullOrEmpty() -> IMAGES
        else -> EXPANDABLE
    }

    override fun getViewHolder(viewType: Int, viewGroup: ViewGroup): StyledViewHolder<HelpInfo> =
        when (viewType) {
            BACKGROUND_IMAGE_COLORED -> HelpInfoBackgroundImageColoredViewHolder.create(viewGroup, style = this.style)
            BACKGROUND_IMAGE -> HelpInfoBackgroundImageViewHolder.create(viewGroup, style = this.style)
            REGULAR -> HelpInfoViewHolder.create(viewGroup, style = this.style)
            BUTTON -> HelpInfoButtonViewHolder.create(viewGroup, style = this.style) {
                onButtonClick.invoke(it)
            }
            LOGO -> HelpInfoLogoViewHolder.create(viewGroup, style = this.style)
            IMAGES -> HelpInfoImageViewHolder.create(viewGroup, style = this.style)
            else -> HelpInfoExpandableViewHolder.create(viewGroup, style = this.style)
        }

}