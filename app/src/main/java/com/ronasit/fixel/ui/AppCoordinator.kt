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
        navController.navigate(R.id.landingFragment)
    }

    override fun toAuthorization() {
        navController.navigate(R.id.authorizationFragment)
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }
}