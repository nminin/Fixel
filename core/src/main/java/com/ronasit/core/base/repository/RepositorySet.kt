package com.ronasit.core.base.repository

import io.reactivex.rxjava3.core.Single

interface RepositorySet<T> {

    fun set(item: T): Single<Unit>
}