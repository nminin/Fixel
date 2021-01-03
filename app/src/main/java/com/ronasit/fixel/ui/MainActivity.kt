package com.ronasit.fixel.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.navigation.findNavController
import com.ronasit.core.navigation.Coordinator
import com.ronasit.core.ui.CustomDialogFragment
import com.ronasit.core.ui.CustomDialogFragment.Companion.DIALOG_FRAGMENT
import com.ronasit.core.ui.CustomDialogHost
import com.ronasit.core.ui.OnFragmentBackPressed
import com.ronasit.core.ui.appbar.AppBarFragment
import com.ronasit.core.ui.appbar.AppBarMenuItem
import com.ronasit.core.ui.appbar.AppBarOwner
import com.ronasit.core.ui.appbar.AppBarStyle
import com.ronasit.fixel.R
import com.ronasit.fixel.ui.appbar.AppBarDecorator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), CustomDialogHost, AppBarOwner {

    private val coordinator by inject<Coordinator>()
    private lateinit var appBarDecorator: AppBarDecorator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (coordinator as AppCoordinator).setNavController(
            findNavController(R.id.nav_host_fragment)
        )
        initAppBar()
    }

    override fun onBackPressed() {
        supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT)?.let {
            (it as CustomDialogFragment).dismiss()
        } ?: kotlin.run {
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
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

    override fun showDialog(fraggment: CustomDialogFragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_dialog, fraggment, DIALOG_FRAGMENT)
            .commitAllowingStateLoss()

    }

    override fun applyAppBarStyle(style: AppBarStyle) {
        appBarDecorator.applyStyle(style)
    }

    override fun applyNavigationItem(items: AppBarMenuItem.Navigation) {

    }

    private fun initAppBar() {
        appBarDecorator = AppBarDecorator(findViewById(R.id.layout_appbar), coordinator)
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            ?.childFragmentManager?.let { hostFragmentManager ->
                hostFragmentManager.addOnBackStackChangedListener {
                    findViewById<FrameLayout>(R.id.layout_appbar).let { appBarLayout ->
                        if (hostFragmentManager.backStackEntryCount > 0) {

                            hostFragmentManager.fragments.get(0).let {
                                if (it is AppBarFragment) {
                                    appBarLayout.visibility = View.VISIBLE
                                } else {
                                    appBarLayout.visibility = View.GONE
                                }
                            }
                        } else {
                            appBarLayout.visibility = View.GONE
                        }
                    }
                }
            }
    }
}