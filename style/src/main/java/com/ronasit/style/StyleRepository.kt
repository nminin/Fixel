package com.ronasit.style

import android.util.Log
import com.ronasit.core.extension.behaviorRelay
import com.ronasit.core.extension.toRelay
import com.ronasit.core.extension.unit
import com.ronasit.core.model.Style
import com.ronasit.core.repository.PreferencesRepository
import com.ronasit.core.repository.StyleRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class StyleRepository(private val preferencesRepository: PreferencesRepository): StyleRepository {

    private val style = behaviorRelay(getDefaultStyle())

    override fun get(): Observable<Style> = style

    override fun getAll(): Observable<List<Style>> = Observable.just(Style.getAll())

    override fun set(item: Style): Single<Unit> = Single.just(item)
        .doOnSuccess {
            preferencesRepository.setDefaultStyle(it.name)
        }
        .toRelay(style)
        .unit()

    private fun getDefaultStyle() = Style.getAll()
        .first {
            it.name == preferencesRepository.getDefaultStyle()
        }
}