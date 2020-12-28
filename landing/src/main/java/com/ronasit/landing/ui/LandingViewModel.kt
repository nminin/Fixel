package com.ronasit.landing.ui

import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.acceptTo
import com.ronasit.core.extension.dispose
import com.ronasit.core.repository.LandingRepository


class LandingViewModel(private val landingRepository: LandingRepository): ViewModel()  {

    init {
        landingRepository.refresh()
            .acceptTo(null, error)
            .dispose(disposeBag)
    }

    fun getLandingModel() = landingRepository.get()
}