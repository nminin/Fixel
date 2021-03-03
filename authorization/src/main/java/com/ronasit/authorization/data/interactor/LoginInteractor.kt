package com.ronasit.authorization.data.interactor

import android.util.Log
import com.ronasit.authorization.data.networking.AuthorizationApi
import com.ronasit.authorization.data.networking.request.LoginRequest
import com.ronasit.authorization.data.networking.response.AuthorizationError
import com.ronasit.authorization.data.networking.response.TokenResponse
import com.ronasit.core.base.SingleInteractor
import com.ronasit.core.repository.PreferencesRepository
import com.ronasit.core.repository.UserRepository
import com.ronasit.networking.singleResponse
import com.ronasit.networking.singleResponseCustomError
import io.reactivex.rxjava3.core.Single
import java.lang.Error

class LoginInteractor(
    private val api: AuthorizationApi,
    private val preferencesRepository: PreferencesRepository,
    private val userRepository: UserRepository
) : SingleInteractor<LoginRequest, Pair<Unit?, AuthorizationError?>> {
    override fun execute(params: LoginRequest): Single<Pair<Unit?, AuthorizationError?>> = api
        .login(params)
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