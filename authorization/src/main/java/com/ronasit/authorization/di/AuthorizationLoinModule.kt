package com.ronasit.authorization.di

import com.google.gson.GsonBuilder
import com.ronasit.authorization.data.interactor.LoginInteractor
import com.ronasit.authorization.data.interactor.SignupInteractor
import com.ronasit.authorization.data.networking.AuthorizationApi
import com.ronasit.authorization.ui.AuthorizationViewModel
import com.ronasit.core.repository.UserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

fun getAuthorizationModule(apiUrl: String) = module {
    factory {
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
                            .addHeader("rws-session-id", UUID.randomUUID().toString())

                        chain.proceed(builder.build())
                    }
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(AuthorizationApi::class.java)
    }

    factory {
        LoginInteractor(get(), get(), get())
    }
    factory {
        SignupInteractor(get(), get(), get())
    }

    viewModel { AuthorizationViewModel(get(), get(), get()) }
}