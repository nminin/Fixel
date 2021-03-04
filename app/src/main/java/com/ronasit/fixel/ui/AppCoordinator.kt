package com.ronasit.fixel.ui

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ronasit.core.navigation.Coordinator
import com.ronasit.fixel.R

class AppCoordinator: Coordinator {
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragmentManager: FragmentManager

    override fun navigateUp() {
        getCurrentNavController()?.let {
            it.navigateUp()
        } ?: run {
            navController.navigateUp()
        }
    }

    override fun toLandingScreen() {
        navController.navigate(R.id.landingFragment)
    }

    override fun toSignUp() {
        bottomNavigationView.selectedItemId = R.id.accountHostFragment
    }

    override fun toLogIn() {
        bottomNavigationView.selectedItemId = R.id.accountHostFragment
    }

    override fun toStyle() {
        getCurrentNavController()?.navigate(R.id.action_menuFragment_to_styleFragment)
    }

    override fun toAccount() {
        getCurrentNavController()?.navigate(R.id.toAccountFragment)
    }

    override fun toAccountInfo() {
        getCurrentNavController()?.navigate(R.id.toAccountInfoFragment)
    }

    override fun toChangePassword() {
        getCurrentNavController()?.navigate(R.id.toChangePasswordFragment)
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun setNavView(bottomNavigationView: BottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView
    }

    fun setFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
    }

    fun getCurrentNavController(): NavController? = if (fragmentManager.fragments.size > 0) {
        fragmentManager.fragments.get(0)
            .childFragmentManager.fragments.get(0)
            .childFragmentManager.fragments.get(0)
            .findNavController()
    } else null
}