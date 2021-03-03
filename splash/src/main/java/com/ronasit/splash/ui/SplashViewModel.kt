package com.ronasit.splash.ui

import com.jakewharton.rxrelay3.PublishRelay
import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.acceptTo
import com.ronasit.core.extension.dispose
import com.ronasit.core.repository.LandingRepository
import com.ronasit.core.repository.UserRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


class SplashViewModel(
    private val landingRepository: LandingRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val onDataPreloaded = PublishRelay.create<Unit>()

    fun isDataPreloaded(): Observable<Unit> = landingRepository.get()
        .map {
            Unit
        }

    fun preloadData() {
        landingRepository.refresh()
            .flatMap {
                userRepository.refresh()
                    .onErrorResumeNext {
                        Single.just(Unit)
                    }
            }
            .acceptTo(onDataPreloaded, error)
            .dispose(disposeBag)
    }
}