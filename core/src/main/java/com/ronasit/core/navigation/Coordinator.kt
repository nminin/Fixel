package com.ronasit.core.navigation

import com.ronasit.core.model.HelpCenterType

interface Coordinator {

    fun navigateUp()

    fun toLandingScreen()

    fun toBrowseScreen()

    fun toLanding()

    fun toBrands()

    fun toSignUp()

    fun toLogIn()

    fun toStyle()

    fun toAccount()

    fun toAccountInfo()

    fun toChangePassword()

    fun toHelp(helpCenterType: HelpCenterType)

}