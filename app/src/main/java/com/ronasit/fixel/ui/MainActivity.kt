package com.ronasit.fixel.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.ronasit.core.navigation.Coordinator
import com.ronasit.core.ui.CustomDialogFragment
import com.ronasit.core.ui.CustomDialogFragment.Companion.DIALOG_FRAGMENT
import com.ronasit.core.ui.CustomDialogHost
import com.ronasit.core.ui.OnFragmentBackPressed
import com.ronasit.fixel.R
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), CustomDialogHost {

    private val coordinator by inject<Coordinator>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (coordinator as AppCoordinator).setNavController(
            findNavController(R.id.nav_host_fragment)
        )
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
}