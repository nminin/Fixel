package com.ronasit.core.base

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class ViewModel: ViewModel() {
    protected val disposeBag = CompositeDisposable()
    protected val error = PublishRelay.create<Throwable>()

    private fun getMessage(): Observable<String> = error
        .map {
            it.localizedMessage ?: ""
        }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }
}