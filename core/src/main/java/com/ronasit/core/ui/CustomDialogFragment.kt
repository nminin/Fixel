package com.ronasit.core.ui

import android.os.Handler

abstract class CustomDialogFragment(layoutId: Int) : Fragment(layoutId) {
    companion object {
        const val DIALOG_FRAGMENT = "DIALOG_FRAGMENT"
    }

    fun dismiss() {
        Handler().postDelayed({
            activity?.supportFragmentManager?.beginTransaction()
                ?.remove(this)
                ?.commitAllowingStateLoss()
        }, 300)
    }

}