package com.ronasit.authorization.data.interactor

import com.ronasit.authorization.data.networking.AuthorizationApi
import com.ronasit.authorization.data.networking.request.LoginRequest
import com.ronasit.authorization.data.networking.request.SignupRequest
import com.ronasit.authorization.data.networking.response.AuthorizationError
import com.ronasit.core.base.SingleInteractor
import com.ronasit.core.repository.PreferencesRepository
import com.ronasit.core.repository.UserRepository
import com.ronasit.networking.singleResponse
import com.ronasit.networking.singleResponseCustomError
import io.reactivex.rxjava3.core.Single

class SignupInteractor(
    private val api: AuthorizationApi,
    private val preferencesRepository: PreferencesRepository,
    private val userRepository: UserRepository
) : SingleInteractor<SignupRequest, Pair<Unit?, AuthorizationError?>> {
    override fun execute(params: SignupRequest): Single<Pair<Unit?, AuthorizationError?>> = api
        .signUp(params)
        .singleResponseCustomError(AuthorizationError::class.java)
        .doOnSuccess {
            it.first?.token?.let {
                preferencesRepository.setToken(it)
            }
        }
        .flatMap { authResponse ->
            if (authResponse.first != null) {
                userRepository.refresh()
                    .map {
                        Unit to authResponse.second
                    }
            } else {
                Single.just(null to authResponse.second)
            }
        }
}