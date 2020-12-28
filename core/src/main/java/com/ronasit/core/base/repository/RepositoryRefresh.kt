package com.ronasit.core.base.repository

import io.reactivex.rxjava3.core.Single

interface RepositoryRefresh<R> {
    fun refresh(): Single<R>
}

interface RepositoryRefreshWithParams<in P,R> {
    fun refresh(params: P): Single<R>
}