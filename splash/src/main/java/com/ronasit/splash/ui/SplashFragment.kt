package com.ronasit.splash.ui

import android.os.Bundle
import android.view.View
import com.ronasit.core.ui.Fragment
import com.ronasit.splash.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment: Fragment(R.layout.fragment_splash) {

    private val splashViewModel by viewModel<SplashViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        splashViewModel.isDataPreloaded()
            .subscribe({
            }, {
                //ignore
            })
        splashViewModel.preloadData()
    }

}