package com.nminin.corearchcomponents.core.data.repository

import io.reactivex.rxjava3.core.Observable

interface RepositoryGetWithParams<PARAMS,RESULT> {
    fun get(params: PARAMS): Observable<RESULT>
}

interface RepositoryGet<RESULT> {
    fun get(): Observable<RESULT>
}