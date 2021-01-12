package com.ronasit.fixel.di

import com.ronasit.core.navigation.Coordinator
import com.ronasit.core.repository.PreferencesRepository
import com.ronasit.core.ui.appbar.AppBarViewModel
import com.ronasit.fixel.data.UserPreferences
import com.ronasit.fixel.ui.AppCoordinator
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getAppKoinModule() = module {
    single<Coordinator> { AppCoordinator() }
    single<PreferencesRepository> { UserPreferences(androidContext()) }
    viewModel { AppBarViewModel() }
}