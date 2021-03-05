package com.ronasit.account.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.ronasit.account.R
import com.ronasit.account.ui.viewmodel.AccountViewModel
import com.ronasit.core.extension.bindView
import com.ronasit.core.extension.highlightsBind
import com.ronasit.core.extension.safeMap
import com.ronasit.core.model.HelpCenterType
import com.ronasit.core.model.HighlightText
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.StyleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment(R.layout.fragment_account) {
    private val styleViewModel by viewModel<StyleViewModel>()
    private val viewModel by viewModel<AccountViewModel>()
    override fun initView(view: View, savedInstanceState: Bundle?) {

        bindView<TextView>(R.id.text_header)
            .highlightsBind(
                viewModel.getUser().safeMap {
                    it.let {
                        HighlightText.fromText(
                            getString(R.string.hello_username),
                            listOf(it.firstName?: "")
                        )
                    }
                }, styleViewModel.getStyle(),
                "#000000"
            )
        bindView<TextView>(R.id.button_account)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_account,
                    listOf(R.string.highlight_my)
                ),
                styleViewModel.getStyle(),
                "#000000"
            )
            .onClick {
                coordinator.toAccountInfo()
            }
        bindView<TextView>(R.id.button_card)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_cards,
                    listOf(R.string.highlight_my)
                ),
                styleViewModel.getStyle(),
                "#000000"
            )
        bindView<TextView>(R.id.button_fbux)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_fbux,
                    listOf(R.string.highlight_my)
                ),
                styleViewModel.getStyle(),
                "#000000"
            )
        bindView<TextView>(R.id.button_orders)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_orders,
                    listOf(R.string.highlight_my)
                ),
                styleViewModel.getStyle(),
                "#000000"
            )
        bindView<TextView>(R.id.button_returns)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_returns,
                    listOf(R.string.highlight_my)
                ),
                styleViewModel.getStyle(),
                "#000000"
            )

        view.findViewById<TextView>(R.id.text_how_to_pay).setOnClickListener {
            coordinator.toHelp(HelpCenterType.HOW_TO_PAY)
        }
        view.findViewById<TextView>(R.id.text_about_us).setOnClickListener {
            coordinator.toHelp(HelpCenterType.ABOUT_US)
        }
        view.findViewById<TextView>(R.id.text_terms).setOnClickListener {
            coordinator.toHelp(HelpCenterType.TERMS_AND_CONDITIONS)
        }
        view.findViewById<TextView>(R.id.text_privacy).setOnClickListener {
            coordinator.toHelp(HelpCenterType.PRIVACY_POLICY)
        }

    }
}