package com.ronasit.core.base

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay3.BehaviorRelay
import com.jakewharton.rxrelay3.PublishRelay
import com.ronasit.core.extension.asObservable
import com.ronasit.core.extension.behaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class ViewModel: ViewModel() {
    protected val disposeBag = CompositeDisposable()
    protected val progress = behaviorRelay(false)
    protected val error = PublishRelay.create<Throwable>()

    fun getProgress() = progress.asObservable()

    fun getError() = error.asObservable()

    private fun getMessage(): Observable<String> = error
        .map {
            it.localizedMessage ?: ""
        }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }
}