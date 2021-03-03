package com.ronasit.core.interactor

import io.reactivex.rxjava3.core.Single

interface LogoutInteractor {
    fun execute(): Single<Unit>
}