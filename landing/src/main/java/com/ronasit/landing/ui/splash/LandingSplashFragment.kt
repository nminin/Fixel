package com.ronasit.landing.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import com.ronasit.core.extension.dispose
import com.ronasit.core.ui.Fragment
import com.ronasit.landing.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LandingSplashFragment: Fragment(R.layout.fragment_splash) {
    private val splashViewModel by viewModel<LandingSplashViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        baseObserve(splashViewModel)
        splashViewModel.getUser()
            .subscribe {
                if (it.value == null) {
                    coordinator.toLanding()
                } else {
                    coordinator.toBrands()
                }
            }
            .dispose(disposable)
    }
}