package com.ronasit.authorization.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.ronasit.authorization.R
import com.ronasit.core.extension.dispose
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.OnFragmentBackPressed
import com.ronasit.core.ui.StyleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthorizationFragment : Fragment(R.layout.fragment_authorization), OnFragmentBackPressed {
    private val styleViewModel by viewModel<StyleViewModel>()
    private val authorizationViewModel by viewModel<AuthorizationViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        authorizationViewModel.getUser()
            .subscribe {
                it.value?.let {
                    coordinator.toAccount()
                } ?: run {
                    view!!.findViewById<LinearLayout>(R.id.content)?.visibility = View.VISIBLE
                }
            }
            .dispose(disposable)
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment(), "login")
            .commit()

        with(view.findViewById<TabLayout>(R.id.tabs)) {

            styleViewModel.getStyle().subscribe {
                this.setSelectedTabIndicatorColor(resources.getColor(it.buttonTintColor))
                this.setTabTextColors(resources.getColor(R.color.white), resources.getColor(it.buttonTextColor))
                view.findViewById<View>(R.id.view_underline).setBackgroundColor(resources.getColor(it.buttonTintColor))
            }
                .dispose(disposable)
            this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    //ignore
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    //ignore
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            view.findViewById<TextView>(R.id.text_title).text =
                                getString(R.string.log_in)
                            childFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, LoginFragment())
                                .commit()
                        }
                        1 -> {
                            view.findViewById<TextView>(R.id.text_title).text =
                                getString(R.string.sign_up)
                            childFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, SignupFragment())
                                .commit()
                        }
                    }
                }
            })
            this.selectTab(this.getTabAt(0))
        }


    }

    override fun onBackPressed() {
        coordinator.toLandingScreen()
    }

}