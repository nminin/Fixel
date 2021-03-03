package com.ronasit.style

import com.ronasit.core.repository.StyleRepository
import com.ronasit.core.ui.StyleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getStyleModule() = module {

    single<StyleRepository> { com.ronasit.style.StyleRepository(get()) }

    viewModel<StyleViewModel> { com.ronasit.style.StyleViewModel(get()) }
    viewModel<ChooseStyleViewModel> { ChooseStyleViewModel(get()) }
}