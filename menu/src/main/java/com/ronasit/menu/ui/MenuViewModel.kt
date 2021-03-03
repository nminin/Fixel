package com.ronasit.menu.ui

import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.acceptTo
import com.ronasit.core.extension.dispose
import com.ronasit.core.interactor.LogoutInteractor
import com.ronasit.core.repository.LandingRepository
import com.ronasit.core.repository.UserRepository

class MenuViewModel(
    private val landingRepository: LandingRepository,
    private val userRepository: UserRepository,
    private val logoutInteractor: LogoutInteractor
): ViewModel() {
    init {
        userRepository.refresh()
            .acceptTo()
            .dispose(disposeBag)
    }

    fun getUser() = userRepository.get()

    fun getCategories() = landingRepository.get()
        .map {
            it.categories
        }

    fun logout() {
        logoutInteractor.execute()
            .acceptTo()
            .dispose(disposeBag)
    }


}