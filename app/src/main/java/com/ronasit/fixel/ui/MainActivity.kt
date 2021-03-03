package com.ronasit.fixel.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ronasit.core.extension.dispose
import com.ronasit.core.navigation.Coordinator
import com.ronasit.core.ui.CustomDialogFragment
import com.ronasit.core.ui.CustomDialogFragment.Companion.DIALOG_FRAGMENT
import com.ronasit.core.ui.CustomDialogHost
import com.ronasit.core.ui.OnFragmentBackPressed
import com.ronasit.core.ui.StyleViewModel
import com.ronasit.fixel.R
import com.ronasit.splash.ui.SplashViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), CustomDialogHost {

    private val coordinator by inject<Coordinator>()
    private val splashViewModel by viewModel<SplashViewModel>()
    private val styleViewModel by viewModel<StyleViewModel>()
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        splashViewModel.isDataPreloaded()
            .subscribe {
                findViewById<ConstraintLayout>(R.id.layout_splash).visibility = View.GONE
            }
            .dispose(disposable)
        (coordinator as AppCoordinator).apply {
            this.setNavController(
                findNavController(R.id.nav_host_fragment)
            )
            this.setFragmentManager(
                supportFragmentManager
            )
        }
        initMenu()
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

    private fun initMenu() {
        with(findViewById<BottomNavigationView>(R.id.bottom_navigation)) {
            (coordinator as AppCoordinator).setNavView(this)
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).let {
                NavigationUI.setupWithNavController(this, it.navController)
            }

            styleViewModel.getStyle()
                .subscribe {
                    this.itemIconTintList = ContextCompat.getColorStateList(
                        context,
                        it.iconTint
                    )
                }

            KeyboardVisibilityEvent.registerEventListener(this@MainActivity) {
                if (it) {
                    this.visibility = View.GONE
                } else {
                    this.visibility = View.VISIBLE
                }
            }
        }
    }
}