package com.ronasit.authorization.ui

import android.util.Log
import com.jakewharton.rxrelay3.PublishRelay
import com.ronasit.authorization.data.interactor.LoginInteractor
import com.ronasit.authorization.data.interactor.SignupInteractor
import com.ronasit.authorization.data.networking.request.LoginRequest
import com.ronasit.authorization.data.networking.request.SignupRequest
import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.*
import com.ronasit.core.repository.UserRepository

class AuthorizationViewModel(
    private val userRepository: UserRepository,
    private val loginInteractor: LoginInteractor,
    private val signupInteractor: SignupInteractor
) : ViewModel() {

    private val onUserLoggedIn = PublishRelay.create<Unit>()
    val emailError = behaviorRelay("")
    val passwordError = behaviorRelay("")
    val phoneError = behaviorRelay("")
    val nameError = behaviorRelay("")

    init {
        userRepository.refresh()
            .acceptTo()
            .dispose(disposeBag)
    }

    fun getUser() = userRepository.get()

    fun login(email: String, password: String) {
        emailError.accept("")
        passwordError.accept("")
        loginInteractor.execute(
            LoginRequest(
                email,
                password
            )
        )
            .progress(progress)
            .doOnSuccess {
                if (it.first != null) {
                    onUserLoggedIn.accept(Unit)
                }
                emailError.accept(it.second?.email ?: "")
                passwordError.accept(it.second?.email ?: "")
                it.second?.message?.let {
                    when {
                        it.contains("Email", true) -> {
                            emailError.accept(it)
                        }
                        it.contains("Password", true) -> {
                            passwordError.accept(it)
                        }

                        else -> {
                            error.accept(Throwable(it))
                        }
                    }
                }
            }
            .acceptTo(null, error)
            .dispose(disposeBag)
    }

    fun signUp(
        name: String,
        email: String,
        mobileNumber: String,
        password: String
    ) {
        emailError.accept("")
        passwordError.accept("")
        nameError.accept("")
        phoneError.accept("")
        signupInteractor.execute(
            SignupRequest(
                email,
                password,
                null,
                name,
                mobileNumber
            )
        )
            .progress(progress)
            .doOnSuccess {
                if (it.first != null) {
                    onUserLoggedIn.accept(Unit)
                }
                it.second?.message?.let {
                    when {
                        it.contains("Email", true) -> {
                            emailError.accept(it)
                        }
                        it.contains("Password", true) -> {
                            passwordError.accept(it)
                        }
                        it.contains("Phone Number", true) -> {
                            phoneError.accept(it)
                        }
                        it.contains("Name", true) -> {
                            nameError.accept(it)
                        }
                        else -> {
                            error.accept(Throwable(it))
                        }
                    }
                }
            }
            .acceptTo(null, error)
            .dispose(disposeBag)
    }

}