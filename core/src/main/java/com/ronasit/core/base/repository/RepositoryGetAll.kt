package com.nminin.corearchcomponents.core.data.repository

import io.reactivex.rxjava3.core.Observable

interface RepositoryGetAll<T> {
    fun getAll(): Observable<T>
}