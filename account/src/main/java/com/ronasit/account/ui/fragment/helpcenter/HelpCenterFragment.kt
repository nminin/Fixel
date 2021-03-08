package com.ronasit.account.ui.fragment.helpcenter

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nminin.bindingbuilder.bind
import com.nminin.bindingbuilder.default.TextDecorator
import com.ronasit.account.R
import com.ronasit.account.ui.fragment.helpcenter.adapter.HelpInfoViewHolderFactory
import com.ronasit.core.extension.bindView
import com.ronasit.core.extension.safeMap
import com.ronasit.core.model.HelpCenterType
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.StyleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HelpCenterFragment : Fragment(R.layout.fragment_help_center) {
    private val viewModel by viewModel<HelpCenterViewModel>()
    private val styleViewModel by viewModel<StyleViewModel>()

    companion object {
        const val HELP_CENTER_PAGE = "HELP_CENTER_PAGE"
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        if (arguments?.containsKey(HELP_CENTER_PAGE) == true) {
            HelpCenterType.getFromString(arguments!!.getString(HELP_CENTER_PAGE)!!)
                ?.let { helpPage ->
                    if (helpPage == HelpCenterType.ABOUT_US){
                        view.findViewById<CardView>(R.id.card_view_host).apply {
                            this.layoutParams = this.layoutParams.apply {
                                (this as LinearLayout.LayoutParams).setMargins(0,0,0,0)
                            }
                        }
                    }
                    viewModel.refresh(helpPage)
                    bindView<ImageView>(R.id.button_back)
                        .onClick {
                            onBackPressed()
                        }
                    bindView<TextView>(R.id.text_header)
                        .observe(viewModel.getHelpPage(helpPage).safeMap {
                            it.name
                        }, TextDecorator())
                    view.findViewById<RecyclerView>(R.id.recycler_view_help)
                        .bind(
                            this, HelpInfoViewHolderFactory(
                                styleViewModel.getStyle().blockingFirst()
                            ) {
                                if (it == "shop_now") {
                                    coordinator.toBrowseScreen()
                                }
                            }
                        )
                        .layoutManager(
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                        )
                        .observeItems(viewModel.getHelpPage(helpPage).map {
                            it.content
                        })
                }

        }
    }

    override fun onBackPressed() {
        coordinator.navigateUp()
    }
}