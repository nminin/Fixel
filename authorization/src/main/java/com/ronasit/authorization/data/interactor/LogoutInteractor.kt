package com.ronasit.authorization.data.interactor

import com.ronasit.core.interactor.LogoutInteractor
import com.ronasit.core.repository.PreferencesRepository
import com.ronasit.core.repository.UserRepository
import io.reactivex.rxjava3.core.Single

class LogoutInteractor(
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository
): LogoutInteractor {
    override fun execute(): Single<Unit> = Single.just(Unit)
        .flatMap {
            preferencesRepository.setToken("")
            userRepository.set(null)
        }
}