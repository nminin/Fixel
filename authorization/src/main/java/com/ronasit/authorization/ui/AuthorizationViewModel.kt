package com.ronasit.authorization.ui

import com.ronasit.authorization.data.interactor.LoginInteractor
import com.ronasit.authorization.data.networking.request.LoginRequest
import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.acceptTo
import com.ronasit.core.extension.dispose
import com.ronasit.core.repository.UserRepository

class AuthorizationViewModel(
    private val userRepository: UserRepository,
    private val loginInteractor: LoginInteractor
): ViewModel() {

    fun login(email: String, password: String) {
        loginInteractor.execute(
            LoginRequest(
                email,
                password
            )
        )
            .acceptTo(null, error)
            .dispose(disposeBag)
    }

}