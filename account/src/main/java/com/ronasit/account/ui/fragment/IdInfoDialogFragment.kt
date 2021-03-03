package com.ronasit.account.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.nminin.bindingbuilder.BindingDecorator
import com.nminin.bindingbuilder.bind
import com.ronasit.account.R
import com.ronasit.core.model.Style
import com.ronasit.core.ui.CustomDialogFragment
import com.ronasit.core.ui.StyleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IdInfoDialogFragment: CustomDialogFragment(R.layout.dialog_id_info) {
    private val styleViewModel by viewModel<StyleViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.layout_host)
            .setOnClickListener {
                dismiss()
            }

        view.findViewById<TextView>(R.id.text_number)
            .bind(this.viewLifecycleOwner)
            .observe(styleViewModel.getStyle(), object: BindingDecorator<TextView, Style>() {
                override fun bind(view: TextView, item: Style) {
                    view.setTextColor(resources.getColor(item.buttonBackgroundColor))
                }
            })
    }
}