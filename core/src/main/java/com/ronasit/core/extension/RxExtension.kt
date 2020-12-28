package com.ronasit.core.extension

import com.jakewharton.rxrelay3.BehaviorRelay
import com.jakewharton.rxrelay3.Relay
import com.nminin.corearchcomponents.core.data.Specification
import com.ronasit.core.base.Mapper
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Exception

fun <T> Observable<T>.progress(liveData: BehaviorRelay<Boolean>) = this
    .doOnSubscribe {
        liveData.accept(true)
    }
    .doOnComplete {
        liveData.accept(false)
    }
    .doOnError {
        liveData.accept(false)
    }

fun <T> Single<T>.progress(liveData: BehaviorRelay<Boolean>) = this
    .doOnSubscribe {
        liveData.accept(true)
    }
    .doOnSuccess {
        liveData.accept(false)
    }
    .doOnError {
        liveData.accept(false)
    }

fun <T> Single<T>.acceptTo(success: Relay<T>? = null, error: Relay<Throwable>? = null) = this
    .subscribe({
        success?.accept(it)
    }, {
        error?.accept(it)
    })


fun <T> Observable<T>.acceptTo(success: Relay<T>? = null, error: Relay<Throwable>? = null) = this
    .subscribe({
        success?.accept(it)
    }, {
        error?.accept(it)
    })

fun Disposable.dispose(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun <T> behaviorRelay(defaultValue: T? = null) = BehaviorRelay.create<T>()
    .apply {
        defaultValue?.let { defaultValue ->
            this.accept(defaultValue)
        }
    }

fun <T> Observable<T>.specificate(specification: Specification<T>) =
    this.filter {
        specification.isSatisfiedBy(it)
    }

fun <T, LIST : List<T>> Observable<LIST>.specificateList(specification: Specification<T>) = this
    .flatMap {
        Observable.fromIterable(it)
    }
    .filter {
        specification.isSatisfiedBy(it)
    }


fun <T, R> Single<T>.mapper(mapper: Mapper<T, R>) = this
    .flatMap {
        mapper.map(it)
    }

fun <T, R> Observable<T>.mapper(mapper: Mapper<T, R>) = this
    .flatMapSingle {
        mapper.map(it)
    }

fun <T> Observable<T>.emptySubscribe() = this
    .subscribe({
        //ignore
    }, {
        it.printStackTrace()
    })

fun <T> Observable<T>.safeMap(action: (T) -> Boolean) = this
    .subscribe({
        //ignore
    }, {
        it.printStackTrace()
    })


