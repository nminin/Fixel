package com.ronasit.fixel.di

import com.ronasit.core.navigation.Coordinator
import com.ronasit.fixel.ui.AppCoordinator
import org.koin.dsl.module

fun getAppKoinModule() = module {
    single<Coordinator> { AppCoordinator() }
}