package com.ronasit.core.ui.appbar

import android.os.Bundle
import com.ronasit.core.ui.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class AppBarFragment(layout: Int) : Fragment(layout) {

    protected val appBarViewModel by viewModel<AppBarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        initAppBar()
        if (savedInstanceState == null) {
            appBarConfiguration()
        }
        super.onCreate(savedInstanceState)
    }

    abstract fun appBarConfiguration()

    private fun initAppBar() {
        getAppBarOwner()?.let { appBarOwner ->
            appBarViewModel.getAppBarStyle()
                .subscribe({
                    appBarOwner.applyAppBarStyle(it)
                }, {
                    //ignore
                })
        }
    }

    private fun getAppBarOwner(): AppBarOwner? = when {
        activity is AppBarOwner -> activity as AppBarOwner
        this.parentFragment != null && this.parentFragment is AppBarOwner ->
            this.parentFragment as AppBarOwner
        else -> null
    }
}