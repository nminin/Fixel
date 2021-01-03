package com.ronasit.core.ui.appbar

interface AppBarOwner {

    fun applyAppBarStyle(style: AppBarStyle)

    fun applyNavigationItem(items: AppBarMenuItem.Navigation)
}