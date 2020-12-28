package com.ronasit.fixel

import android.app.Application
import com.ronasit.fixel.di.getAppKoinModule
import com.ronasit.landing.di.getLandingViewModel
import org.koin.core.context.startKoin

class FixelApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            this.modules(
                listOf(
                    getAppKoinModule(),
                    getLandingViewModel(BuildConfig.SERVER_URL)
                )
            )
        }
    }
}