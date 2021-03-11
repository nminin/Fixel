package com.ronasit.account.ui.viewmodel

import com.jakewharton.rxrelay3.BehaviorRelay
import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.safeSubscribe
import com.ronasit.core.extension.dispose
import com.ronasit.core.extension.progress
import com.ronasit.core.model.User
import com.ronasit.core.repository.UserRepository
import io.reactivex.rxjava3.core.Observable

class AccountViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val userInfo = BehaviorRelay.create<User>()

    init {
        userRepository
            .refresh()
            .safeSubscribe()
            .dispose(disposeBag)
        userRepository.get()
            .map {
                it.value?.copy()
            }
            .safeSubscribe(userInfo)
            .dispose(disposeBag)
    }

    fun getUser() = userInfo

    fun isSaveAvailable(): Observable<Boolean> = userRepository.get().flatMap { user ->
        userInfo.map { changedUser ->
            !changedUser.firstName.isNullOrEmpty()
                    && !changedUser.lastName.isNullOrEmpty()
                    && !changedUser.email.isNullOrEmpty()
                    && !changedUser.phoneNumber.isNullOrEmpty()
                    && (changedUser.firstName != user.value?.firstName
                    || changedUser.lastName != user.value?.lastName
                    || changedUser.email != user.value?.email
                    || changedUser.gender != user.value?.gender
                    || changedUser.phoneNumber != user.value?.phoneNumber
                    || changedUser.identificationNumber != user.value?.identificationNumber)
        }
    }

    fun saveChanges() {
        userRepository.update(
            userInfo.value
        )
            .progress(progress)
            .safeSubscribe()
            .dispose(disposeBag)
    }

    fun setFirstName(value: String) {
        userInfo.value?.let {
            if (it.firstName != value) {
                userInfo.accept(
                    it.apply {
                        this.firstName = value
                    }
                )
            }
        }
    }

    fun setLastName(value: String) {
        userInfo.value?.let {
            if (it.lastName != value) {
                userInfo.accept(
                    it.apply {
                        this.lastName = value
                    }
                )
            }
        }
    }
    fun setGender(value: User.Gender?) {
        userInfo.value?.let {
            if (it.gender != value) {
                userInfo.accept(
                    it.apply {
                        this.gender = value
                    }
                )
            }
        }
    }

    fun setEmail(value: String) {
        userInfo.value?.let {
            if (it.email != value) {
                userInfo.accept(
                    it.apply {
                        this.email = value
                    }
                )
            }
        }
    }

    fun setPhone(value: String) {
        userInfo.value?.let {
            if (it.phoneNumber != value) {
                userInfo.accept(
                    it.apply {
                        this.phoneNumber = value
                    }
                )
            }
        }
    }

    fun setID(value: String) {
        userInfo.value?.let {
            if (it.identificationNumber != value) {
                userInfo.accept(
                    it.apply {
                        this.identificationNumber = value
                    }
                )
            }
        }
    }
}