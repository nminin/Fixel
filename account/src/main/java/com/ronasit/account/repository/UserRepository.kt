package com.ronasit.account.repository

import com.jakewharton.rxrelay3.BehaviorRelay
import com.ronasit.account.model.UpdateAccountInfo
import com.ronasit.account.networking.AccountApi
import com.ronasit.core.extension.accepTo
import com.ronasit.core.extension.behaviorRelay
import com.ronasit.core.extension.toOptional
import com.ronasit.core.extension.unit
import com.ronasit.core.model.Optional
import com.ronasit.core.model.User
import com.ronasit.core.repository.UserRepository
import com.ronasit.networking.singleResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

internal class UserRepository(
    private val api: AccountApi
) :
    UserRepository {

    private var data = BehaviorRelay.create<Optional<User>>()

    override fun refresh(): Single<Unit> = api.getAccount()
        .singleResponse()
        .doOnSuccess {
            data.accept(Optional(it))
        }
        .doOnError {
            data.accept(Optional(null))
        }
        .unit()

    override fun get(): Observable<Optional<User>> = data

    override fun set(item: User?): Single<Unit> = Single.just(Unit)
        .doOnSuccess {
            data.accept(Optional(null))
        }

    override fun update(item: User): Single<Unit> = api.updateAccount(
        UpdateAccountInfo.fromUser(item)
    )
        .singleResponse()
        .toOptional()
        .accepTo(data)
        .flatMap {
            refresh()
        }

}