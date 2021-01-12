package com.ronasit.fixel.ui.appbar

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ronasit.core.navigation.Coordinator
import com.ronasit.core.ui.appbar.AppBarStyle
import com.ronasit.fixel.R

class AppBarDecorator(view: View, coordinator: Coordinator) {

    private val profileMenuItem = view.findViewById<ImageView>(R.id.item_profile)
    private val background = view.findViewById<ConstraintLayout>(R.id.layout_background)
    private val titleImage = view.findViewById<ImageView>(R.id.image_logo)

    init {
        profileMenuItem.setOnClickListener {
            coordinator.toAuthorization()
        }
    }

    fun applyStyle(style: AppBarStyle) {
        when (style) {
            AppBarStyle.TRANSPARENT -> {
                profileMenuItem.visibility = View.VISIBLE
                profileMenuItem.setImageResource(R.drawable.ic_profile_transparent)
                background.setBackgroundResource(R.color.transparent)
                titleImage.setImageResource(R.drawable.ic_appbar_logo_light)
            }
            AppBarStyle.WHITE -> {
                profileMenuItem.visibility = View.VISIBLE
                profileMenuItem.setImageResource(R.drawable.ic_profile_transparent)
                background.setBackgroundResource(R.color.white)
                titleImage.setImageResource(R.drawable.ic_appbar_logo_dark)
            }
            AppBarStyle.AUTHORIZATION -> {
                profileMenuItem.visibility = View.GONE
                background.setBackgroundResource(R.color.transparent)
                titleImage.setImageResource(R.drawable.ic_appbar_logo_dark)
            }

        }
    }

}