package com.ronasit.style

import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.safeSubscribe
import com.ronasit.core.extension.asObservable
import com.ronasit.core.extension.behaviorRelay
import com.ronasit.core.extension.dispose
import com.ronasit.core.model.Style
import com.ronasit.core.repository.StyleRepository
import io.reactivex.rxjava3.core.Observable

class ChooseStyleViewModel(private val styleRepository: StyleRepository): ViewModel() {

    private val selectedStyle = behaviorRelay(styleRepository.get().blockingFirst())

    init {
        styleRepository.get()
            .safeSubscribe(selectedStyle)
            .dispose(disposeBag)
    }

    fun setStyle(style: Style) {
        selectedStyle.accept(style)
    }

    fun getSelectedStyle() = selectedStyle.asObservable()

    fun saveChanges() {
        selectedStyle.firstOrError()
            .flatMap {
                styleRepository.set(it)
            }
            .safeSubscribe()
            .dispose(disposeBag)
    }

    fun getAllStyles(): Observable<List<Style>> = styleRepository.getAll()
}