package com.ronasit.splash.di

import com.ronasit.splash.ui.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getSplashKoinModule() = module {
    viewModel { SplashViewModel(get(), get()) }
}