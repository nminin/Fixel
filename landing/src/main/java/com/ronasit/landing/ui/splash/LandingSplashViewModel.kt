package com.ronasit.landing.ui.splash

import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.dispose
import com.ronasit.core.extension.safeSubscribe
import com.ronasit.core.repository.UserRepository

class LandingSplashViewModel(
    private val userRepository: UserRepository): ViewModel() {
    init {
        userRepository.refresh()
            .safeSubscribe(null, error)
            .dispose(disposeBag)
    }
    fun getUser() = userRepository.get()
}