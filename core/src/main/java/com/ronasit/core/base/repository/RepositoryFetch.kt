package com.ronasit.core.base.repository

import io.reactivex.rxjava3.core.Single

interface RepositoryFetch<P,R> {

    fun fetch(value: P) : Single<R>
}