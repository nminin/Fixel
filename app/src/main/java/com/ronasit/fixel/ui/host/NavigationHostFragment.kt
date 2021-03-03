package com.ronasit.fixel.ui.host

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.OnFragmentBackPressed
import com.ronasit.fixel.R

abstract class NavigationHostFragment(private val navigationId: Int) :
    Fragment(R.layout.navigation_host_fragment), OnFragmentBackPressed {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).let {
            it.navController.setGraph(navigationId)
        }
    }
    override fun initView(view: View, savedInstanceState: Bundle?) {

    }

    override fun onBackPressed() {
        childFragmentManager.findFragmentById(R.id.nav_host_fragment)
            ?.childFragmentManager?.let { fManager ->
                if (fManager.fragments.size > 0) {
                    fManager.fragments.get(0).let {
                        if (it is OnFragmentBackPressed) {
                            it.onBackPressed()
                        } else {
                            super.onBackPressed()
                        }
                    }
                } else {
                    super.onBackPressed()
                }
            } ?: kotlin.run {
            super.onBackPressed()
        }
    }
}