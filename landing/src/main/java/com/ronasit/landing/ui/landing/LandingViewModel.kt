package com.ronasit.landing.ui.landing

import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.safeSubscribe
import com.ronasit.core.extension.dispose
import com.ronasit.core.repository.LandingRepository


class LandingViewModel(private val landingRepository: LandingRepository): ViewModel()  {

    init {
        landingRepository.refresh()
            .safeSubscribe(null, error)
            .dispose(disposeBag)
    }

    fun getLandingModel() = landingRepository.get()
}