package com.ronasit.landing.di

import com.google.gson.GsonBuilder
import com.ronasit.core.dao.BrandsLandingDao
import com.ronasit.core.repository.LandingRepository
import com.ronasit.core.repository.PreferencesRepository
import com.ronasit.landing.data.BrandsDao
import com.ronasit.landing.data.BrandsRepository
import com.ronasit.landing.data.networking.LandingApi
import com.ronasit.landing.ui.brands.BrandsViewModel
import com.ronasit.landing.ui.landing.LandingViewModel
import com.ronasit.landing.ui.splash.LandingSplashViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

fun getLandingViewModel(apiUrl: String) = module {
    factory {
        getLandingApi(apiUrl, get())
    }

    single<LandingRepository> {
        com.ronasit.landing.data.LandingRepository(get())
    }

    single { BrandsRepository(get()) }

    single<BrandsLandingDao> { BrandsDao(get()) }

    viewModel { LandingViewModel(get()) }
    viewModel { BrandsViewModel(get()) }
    viewModel { LandingSplashViewModel(get()) }
}

private fun getLandingApi(apiUrl: String, preferencesRepository: PreferencesRepository) =
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
        .create(LandingApi::class.java)

