package com.ronasit.authorization.data.interactor

import com.ronasit.authorization.data.networking.AuthorizationApi
import com.ronasit.authorization.data.networking.request.LoginRequest
import com.ronasit.core.base.SingleInteractor
import com.ronasit.core.repository.PreferencesRepository
import com.ronasit.core.repository.UserRepository
import com.ronasit.networking.singleResponse
import io.reactivex.rxjava3.core.Single

class LoginInteractor(
    private val api: AuthorizationApi,
    private val preferencesRepository: PreferencesRepository,
    private val userRepository: UserRepository
) : SingleInteractor<LoginRequest, Unit> {
    override fun execute(params: LoginRequest): Single<Unit> = api
        .login(params)
        .singleResponse()
        .doOnSuccess {
            preferencesRepository.setToken(it.token ?: "")
        }
        .flatMap {
            userRepository.refresh()
        }
        .map {
            Unit
        }
}