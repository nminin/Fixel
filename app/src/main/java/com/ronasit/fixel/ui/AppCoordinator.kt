package com.ronasit.fixel.ui

import androidx.navigation.NavController
import com.ronasit.core.navigation.Coordinator
import com.ronasit.fixel.R

class AppCoordinator: Coordinator {
    private lateinit var navController: NavController

    override fun toFbuxInfo() {
        navController.navigate(R.id.action_landingFragment_to_infoDialogFragment)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }
}