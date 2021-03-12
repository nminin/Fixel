package com.ronasit.landing.ui.brands

import android.util.Log
import com.ronasit.core.base.ViewModel
import com.ronasit.core.dao.BrandsLandingDao
import com.ronasit.core.extension.progress
import com.ronasit.core.extension.safeMap
import com.ronasit.core.extension.safeSubscribe

class BrandsViewModel(
    private val brandsLandingDao: BrandsLandingDao
): ViewModel() {

    init {
        refresh()
    }

    fun getLandingHeader() = brandsLandingDao.getHeader()

    fun getBrands() = brandsLandingDao.getContent().safeMap {
        it.brands
    }

    fun getTitle() = brandsLandingDao.getContent().safeMap {
        it.title
    }

    fun getButton() = brandsLandingDao.getContent().safeMap {
        it.buttonAllBrands
    }

    fun refresh() {
        brandsLandingDao.refresh()
            .progress(progress)
            .safeSubscribe(null, error)
    }
}