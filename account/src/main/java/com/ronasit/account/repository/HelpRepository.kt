package com.ronasit.account.repository

import android.util.Log
import com.jakewharton.rxrelay3.BehaviorRelay
import com.nminin.corearchcomponents.core.data.repository.RepositoryGetWithParams
import com.ronasit.account.model.HelpPage
import com.ronasit.account.networking.AccountApi
import com.ronasit.core.base.repository.RepositoryRefreshWithParams
import com.ronasit.core.extension.behaviorRelay
import com.ronasit.core.extension.safeMap
import com.ronasit.core.extension.unit
import com.ronasit.core.model.HelpCenterType
import com.ronasit.networking.singleResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.*

class HelpRepository(
    private val accountApi: AccountApi
) : RepositoryGetWithParams<HelpCenterType, HelpPage>,
    RepositoryRefreshWithParams<HelpCenterType, Unit> {

    private val data = behaviorRelay(hashMapOf<HelpCenterType, HelpPage>())

    override fun get(params: HelpCenterType): Observable<HelpPage> = data
        .safeMap {
            it.get(params)
        }

    override fun refresh(params: HelpCenterType): Single<Unit> = accountApi.getHelpInfo(
        params.domain
    ).singleResponse()
        .doOnSuccess {page ->
            data.accept(data.value.apply {
                this[params] = page
            })
        }
        .unit()
}