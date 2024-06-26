package com.ronasit.account.di

import com.google.gson.GsonBuilder
import com.ronasit.account.interactor.ChangePasswordInteractor
import com.ronasit.account.networking.AccountApi
import com.ronasit.account.repository.HelpRepository
import com.ronasit.account.ui.fragment.helpcenter.HelpCenterViewModel
import com.ronasit.account.ui.viewmodel.AccountViewModel
import com.ronasit.account.ui.viewmodel.ChangePasswordViewModel
import com.ronasit.core.repository.PreferencesRepository
import com.ronasit.core.repository.UserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun getAccountModule(apiUrl: String) = module {
    factory {
        getAccountApi(apiUrl, get())
    }

    single<UserRepository> {
        com.ronasit.account.repository.UserRepository(get())
    }
    single { HelpRepository(get()) }

    factory { ChangePasswordInteractor(get()) }

    viewModel { AccountViewModel(get()) }
    viewModel { ChangePasswordViewModel(androidContext(), get()) }
    viewModel { HelpCenterViewModel(get()) }
}

private fun getAccountApi(apiUrl: String, preferencesRepository: PreferencesRepository) =
    Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setPrettyPrinting()
                    .excludeFieldsWithoutExposeAnnotation()
                    .enableComplexMapKeySerialization()
                    .setVersion(1.0)
                    .create()
            )
        )
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                })
                .addInterceptor { chain ->
                    val builder = chain
                        .request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .addHeader("Platform", "android")
                        .addHeader("rws-session-id", preferencesRepository.getUUID())
                        .addHeader("x-rws-token", preferencesRepository.getToken())
                    chain.proceed(builder.build())
                }
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
        )
        .build()
        .create(AccountApi::class.java)