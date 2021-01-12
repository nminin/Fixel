package com.ronasit.authorization.data.repository

import com.ronasit.core.model.User
import com.ronasit.core.repository.PreferencesRepository
import com.ronasit.core.repository.UserRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

internal class UserRepository(
    private val preferencesRepository: PreferencesRepository
) :
    UserRepository {
    override fun refresh(): Single<User> {
        TODO("Not yet implemented")
    }

    override fun get(): Observable<User> {
        TODO("Not yet implemented")
    }
}