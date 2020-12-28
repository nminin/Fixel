package com.ronasit.landing.data

import com.jakewharton.rxrelay3.BehaviorRelay
import com.ronasit.core.base.mapper
import com.ronasit.core.model.Landing
import com.ronasit.core.repository.LandingRepository
import com.ronasit.landing.data.mapper.LandingMapper
import com.ronasit.landing.data.networking.LandingApi
import com.ronasit.networking.singleResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

internal class LandingRepository(
    private val api: LandingApi
) : LandingRepository {

    private val value: BehaviorRelay<Landing> = BehaviorRelay
        .create<Landing>()

    override fun get(): Observable<Landing> = value

    override fun refresh(): Single<Unit> = api.getLanding()
        .singleResponse()
        .mapper(LandingMapper())
        .doOnSuccess {
            value.accept(it)
        }
        .map {
            Unit
        }
}