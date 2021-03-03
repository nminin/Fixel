package com.ronasit.style

import com.ronasit.core.extension.dispose
import com.ronasit.core.model.Style
import com.ronasit.core.repository.StyleRepository
import com.ronasit.core.ui.StyleViewModel
import io.reactivex.rxjava3.core.Observable

internal class StyleViewModel(private val styleRepository: StyleRepository): StyleViewModel() {

    override fun setStyle(style: Style) {
        styleRepository.set(style)
            .subscribe()
            .dispose(disposeBag)
    }

    override fun getStyle(): Observable<Style> = styleRepository.get()

    override fun getAllStyles(): Observable<List<Style>> = styleRepository.getAll()
}