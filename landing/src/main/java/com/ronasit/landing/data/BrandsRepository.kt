package com.ronasit.landing.data

import com.jakewharton.rxrelay3.BehaviorRelay
import com.nminin.corearchcomponents.core.data.repository.RepositoryGet
import com.ronasit.core.base.repository.RepositoryRefresh
import com.ronasit.core.extension.accepTo
import com.ronasit.core.extension.unit
import com.ronasit.landing.data.networking.LandingApi
import com.ronasit.landing.data.networking.response.BrandsResponse
import com.ronasit.networking.singleResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

internal class BrandsRepository(
    private val api: LandingApi
) : RepositoryGet<BrandsResponse>,
    RepositoryRefresh<Unit> {
    private val data = BehaviorRelay.create<BrandsResponse>()

    override fun get(): Observable<BrandsResponse> = data

    override fun refresh(): Single<Unit> = api.getBrands()
        .singleResponse()
        .accepTo(data)
        .unit()

}