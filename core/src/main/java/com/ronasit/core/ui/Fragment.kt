package com.ronasit.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ronasit.core.navigation.Coordinator
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.android.ext.android.inject

abstract class Fragment(
    private val layoutId: Int
) : Fragment() {

    protected val disposable = CompositeDisposable()
    protected val coordinator by inject<Coordinator>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = layoutInflater.inflate(layoutId, container, false)
        initView(view, savedInstanceState)
        return view
    }

    abstract fun initView(view: View, savedInstanceState: Bundle?)

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}