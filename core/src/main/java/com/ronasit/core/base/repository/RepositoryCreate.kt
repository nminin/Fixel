package com.nminin.corearchcomponents.core.data.repository

import io.reactivex.rxjava3.core.Single

interface RepositoryCreate<P,R> {

    fun create(value: P) : Single<R>
}