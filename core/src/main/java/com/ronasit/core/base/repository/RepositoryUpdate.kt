package com.ronasit.core.base.repository

import io.reactivex.rxjava3.core.Single

interface RepositoryUpdate<T> {

    fun update(item: T): Single<Unit>
}