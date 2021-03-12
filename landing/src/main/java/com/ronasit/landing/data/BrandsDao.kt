package com.ronasit.landing.data

import android.util.Log
import com.ronasit.core.dao.BrandsLandingDao
import com.ronasit.core.extension.safeMap
import com.ronasit.core.model.BrandsContent
import com.ronasit.core.model.BrandsHeader
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

internal class BrandsDao(
    private val brandsRepository: BrandsRepository
) : BrandsLandingDao {
    override fun getHeader(): Observable<BrandsHeader> = brandsRepository.get()
        .safeMap {
            it.header
        }

    override fun getContent(): Observable<BrandsContent> = brandsRepository.get()
        .safeMap {
            it.content
        }

    override fun refresh(): Single<Unit> = brandsRepository.refresh()
}