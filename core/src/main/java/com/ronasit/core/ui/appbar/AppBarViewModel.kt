package com.ronasit.core.ui.appbar

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable


class AppBarViewModel: ViewModel() {

    private val style = BehaviorRelay.create<AppBarStyle>().apply {
        this.accept(AppBarStyle.WHITE)
    }

    private val navigationItem = BehaviorRelay.create<AppBarMenuItem.Navigation>().apply {
        this.accept(AppBarMenuItem.Navigation.INVISIBLE)
    }

    fun getNavigationItem(): Observable<AppBarMenuItem.Navigation> = navigationItem

    fun setNavigationItem(type: AppBarMenuItem.Navigation) {
        navigationItem.accept(type)
    }

    fun setStyle(style: AppBarStyle) {
        if (this.style.value != style) {
            this.style.accept(style)
        }
    }

    fun getAppBarStyle(): Observable<AppBarStyle> = style
}