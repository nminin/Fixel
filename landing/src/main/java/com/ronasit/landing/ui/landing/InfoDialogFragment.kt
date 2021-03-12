package com.ronasit.landing.ui.landing

import android.os.Bundle
import android.view.View
import com.ronasit.core.ui.CustomDialogFragment
import com.ronasit.core.ui.Fragment
import com.ronasit.landing.R

class InfoDialogFragment: CustomDialogFragment(R.layout.fragment_fbux_info) {

    override fun initView(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.layout_host)
            .setOnClickListener {
                dismiss()
            }
    }

}