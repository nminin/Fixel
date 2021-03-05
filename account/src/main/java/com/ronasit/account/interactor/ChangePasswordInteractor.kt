package com.ronasit.account.interactor

import com.ronasit.account.networking.AccountApi
import com.ronasit.account.networking.model.ChangePasswordRequest
import com.ronasit.core.base.SingleInteractor
import com.ronasit.networking.singleResponse
import io.reactivex.rxjava3.core.Single

class ChangePasswordInteractor(
    private val api: AccountApi
) : SingleInteractor<Pair<String, String>, Unit> {

    override fun execute(params: Pair<String, String>): Single<Unit> = api.changePassword(
        ChangePasswordRequest(
            params.first,
            params.second
        )
    )
        .singleResponse()
}