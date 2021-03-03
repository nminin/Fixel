package com.ronasit.fixel

import android.app.Application
import com.ronasit.account.di.getAccountModule
import com.ronasit.authorization.di.getAuthorizationModule
import com.ronasit.fixel.di.getAppKoinModule
import com.ronasit.landing.di.getLandingViewModel
import com.ronasit.menu.ui.di.getMenuKoinModule
import com.ronasit.splash.di.getSplashKoinModule
import com.ronasit.style.getStyleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FixelApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            this.androidContext(this@FixelApplication)
            this.modules(
                listOf(
                    getAppKoinModule(),
                    getLandingViewModel(BuildConfig.SERVER_URL),
                    getAccountModule(BuildConfig.SERVER_URL),
                    getAuthorizationModule(BuildConfig.SERVER_URL),
                    getSplashKoinModule(),
                    getStyleModule(),
                    getMenuKoinModule()
                )
            )
        }
    }
}