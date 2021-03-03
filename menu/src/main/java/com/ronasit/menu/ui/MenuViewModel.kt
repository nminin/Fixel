package com.ronasit.menu.ui

import com.ronasit.core.base.ViewModel
import com.ronasit.core.repository.LandingRepository
import com.ronasit.core.repository.UserRepository

class MenuViewModel(
    private val landingRepository: LandingRepository,
    private val userRepository: UserRepository
): ViewModel() {

    fun getCategories() = landingRepository.get()
        .map {
            it.categories
        }


}