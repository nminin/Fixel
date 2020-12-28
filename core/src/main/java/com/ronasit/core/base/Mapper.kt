package com.ronasit.core.base

import io.reactivex.rxjava3.core.Single

interface Mapper<in T, R> {
    fun map(input: T): Single<R>
}

fun <T, R> Single<T>.mapper(mapper: Mapper<T, R>) = this
    .flatMap {
        mapper.map(it)
    }