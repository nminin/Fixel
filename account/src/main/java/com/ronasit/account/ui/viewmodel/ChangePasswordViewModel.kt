package com.ronasit.account.ui.viewmodel

import android.content.Context
import android.util.Log
import com.jakewharton.rxrelay3.PublishRelay
import com.ronasit.account.R
import com.ronasit.account.interactor.ChangePasswordInteractor
import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.acceptTo
import com.ronasit.core.extension.asObservable
import com.ronasit.core.extension.behaviorRelay
import com.ronasit.core.extension.dispose
import com.ronasit.core.model.Optional
import io.reactivex.rxjava3.core.Observable

class ChangePasswordViewModel(
    private val context: Context,
    private val changePasswordInteractor: ChangePasswordInteractor
) : ViewModel() {

    private val oldPasswordError = behaviorRelay(Optional<String>(null))
    private val newPasswordError = behaviorRelay(Optional<String>(null))
    private val repeatPasswordError = behaviorRelay(Optional<String>(null))
    private val oldPassword = behaviorRelay("")
    private val newPassword = behaviorRelay("")
    private val repeatPassword = behaviorRelay("")
    private val onPasswordChanged = PublishRelay.create<Unit>()

    fun getOldPasswordError() = oldPasswordError.asObservable()
    fun getNewPasswordError() = newPasswordError.asObservable()
    fun getRepeatPasswordError() = repeatPasswordError.asObservable()
    fun onPasswordChanged() = onPasswordChanged.asObservable()

    fun setOldPassword(value: String) {
        oldPassword.accept(value)
    }

    fun setNewPassword(value: String) {
        newPassword.accept(value)
    }

    fun setRepeatPassword(value: String) {
        repeatPassword.accept(value)
    }

    fun isSaveChangesAvailable(): Observable<Boolean> =
        oldPassword.flatMap { oldPassword ->
            newPassword.flatMap { newPassword ->
                repeatPassword.map { repeatPassword ->
                    if (oldPassword.length in 3..6) {
                        oldPasswordError.accept(Optional(context.getString(R.string.hint_old_password_to_short)))
                    } else {
                        oldPasswordError.accept(Optional(null))
                    }
                    if (newPassword.length in 3..6) {
                        newPasswordError.accept(Optional(context.getString(R.string.hint_password_to_short)))
                    } else {
                        newPasswordError.accept(Optional(null))
                    }
                    if (newPassword.length > 3 && repeatPassword.length > 3 && !newPassword.equals(
                            repeatPassword
                        )
                    ) {
                        repeatPasswordError.accept(Optional(context.getString(R.string.passwords_not_match)))
                    } else {
                        repeatPasswordError.accept(Optional(null))
                    }

                    when {
                        newPassword.length < 7 -> {
                            false
                        }
                        !newPassword.equals(repeatPassword) -> {
                            false
                        }
                        else -> {
                            newPasswordError.accept(Optional(null))
                            repeatPasswordError.accept(Optional(null))
                            true
                        }
                    }
                }
            }
        }

    fun changePassword() {
        changePasswordInteractor.execute(
            oldPassword.value!! to newPassword.value!!
        )
            .doOnError {
                oldPasswordError.accept(Optional(it.localizedMessage))
            }
            .acceptTo(onPasswordChanged)
            .dispose(disposeBag)

    }
}