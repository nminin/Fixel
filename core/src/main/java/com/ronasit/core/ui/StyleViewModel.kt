package com.ronasit.core.ui

import com.ronasit.core.base.ViewModel
import com.ronasit.core.model.Style
import io.reactivex.rxjava3.core.Observable

abstract class StyleViewModel(): ViewModel() {

    abstract fun setStyle(style: Style)

    abstract fun getStyle(): Observable<Style>

    abstract fun getAllStyles(): Observable<List<Style>>

}