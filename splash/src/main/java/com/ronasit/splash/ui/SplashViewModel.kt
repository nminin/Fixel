package com.ronasit.splash.ui

import com.jakewharton.rxrelay3.PublishRelay
import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.acceptTo
import com.ronasit.core.extension.dispose
import com.ronasit.core.repository.LandingRepository
import io.reactivex.rxjava3.core.Observable


class SplashViewModel(
    private val landingRepository: LandingRepository
) : ViewModel() {

    private val onDataPreloaded = PublishRelay.create<Unit>()

    fun onDataPreloaded(): Observable<Unit> = onDataPreloaded

    fun preloadData() {
        landingRepository.refresh()
            .acceptTo(onDataPreloaded, error)
            .dispose(disposeBag)
    }
}