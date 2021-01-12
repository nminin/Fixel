package com.ronasit.fixel.data

import android.content.Context
import com.chibatching.kotpref.KotprefModel
import com.ronasit.core.repository.PreferencesRepository

class UserPreferences(context: Context) : KotprefModel(context), PreferencesRepository {
    private var authToken by stringPref(key = "AUTH_TOKEN", default = "")

    override fun setToken(value: String) {
        authToken = value
    }

    override fun getToken(): String = authToken
}