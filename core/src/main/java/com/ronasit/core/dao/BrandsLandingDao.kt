package com.ronasit.core.dao

import com.ronasit.core.model.BrandsContent
import com.ronasit.core.model.BrandsHeader
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface BrandsLandingDao {
    fun getHeader(): Observable<BrandsHeader>

    fun getContent(): Observable<BrandsContent>

    fun refresh(): Single<Unit>
}