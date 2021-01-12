package com.ronasit.core.base

import io.reactivex.rxjava3.core.Single

interface SingleInteractor<P,R> {

    fun execute(params: P) : Single<R>
}