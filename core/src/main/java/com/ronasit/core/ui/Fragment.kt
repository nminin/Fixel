package com.ronasit.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.dispose
import com.ronasit.core.navigation.Coordinator
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.android.ext.android.inject

abstract class Fragment(
    private val layoutId: Int
) : Fragment(), OnFragmentBackPressed {

    protected val disposable = CompositeDisposable()
    protected val coordinator by inject<Coordinator>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view, savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
    }

    fun baseObserve(viewModel: ViewModel) {
        viewModel.getError()
            .subscribe {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            .dispose(disposable)
    }

    abstract fun initView(view: View, savedInstanceState: Bundle?)

    override fun onDestroyView() {
        disposable.dispose()
        super.onDestroyView()
    }

    override fun onBackPressed() {
        activity?.finish()
    }
}