package com.ronasit.fixel.ui

import androidx.navigation.NavController
import com.ronasit.core.navigation.Coordinator
import com.ronasit.fixel.R

class AppCoordinator: Coordinator {
    private lateinit var navController: NavController

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun toLandingScreen() {
        navController.navigate(R.id.action_splashFragment_to_landingFragment)
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }
}