package com.nminin.bindingbuilder


import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable


class BindingBuilder<V : View>(
    val view: V,
    private val lifecycleOwner: LifecycleOwner
) : LifecycleObserver {

    val disposable = CompositeDisposable()

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    fun <A> observe(observable: Observable<A>, decorator: BindingDecorator<V, A>) = this.apply{
        disposable.add(observable.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                decorator.bind(view, it)
            }, {

            })
        )
    }

    fun onClick(action: ()-> Unit) = this.apply{
        view.setOnClickListener {
            action.invoke()
        }
    }

    fun progress(observable: Observable<Boolean>, callback: (isInProgress: Boolean) -> Unit) = this.apply{
        disposable.add(observable.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.invoke(it)
            },{

            })
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        disposable.dispose()
    }
}