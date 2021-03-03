package com.ronasit.menu.ui.di

import com.ronasit.menu.ui.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getMenuKoinModule() = module {

    viewModel { MenuViewModel(get(), get()) }
}