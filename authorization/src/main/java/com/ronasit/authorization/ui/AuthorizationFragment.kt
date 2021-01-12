package com.ronasit.authorization.ui

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.ronasit.authorization.R
import com.ronasit.core.ui.OnFragmentBackPressed
import com.ronasit.core.ui.appbar.AppBarFragment
import com.ronasit.core.ui.appbar.AppBarStyle

class AuthorizationFragment : AppBarFragment(R.layout.fragment_authorization), OnFragmentBackPressed {

    override fun initView(view: View, savedInstanceState: Bundle?) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment(), "login")
            .commit()
        with(view.findViewById<TabLayout>(R.id.tabs)) {
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

        view.findViewById<ScrollView>(R.id.scroll_view)
            .setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY <= 10) {
                    appBarViewModel.setStyle(AppBarStyle.AUTHORIZATION)
                } else {
                    appBarViewModel.setStyle(AppBarStyle.WHITE)
                }
            }
    }

    override fun onBackPressed() {
        coordinator.toLandingScreen()
    }

    override fun appBarConfiguration() {
        appBarViewModel.setStyle(AppBarStyle.AUTHORIZATION)
    }

}