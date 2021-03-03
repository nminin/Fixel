package com.ronasit.fixel.data

import android.content.Context
import com.chibatching.kotpref.KotprefModel
import com.ronasit.core.model.Style
import com.ronasit.core.repository.PreferencesRepository
import java.util.*

class UserPreferences(context: Context) : KotprefModel(context), PreferencesRepository {
    private var authToken by stringPref(key = "AUTH_TOKEN", default = "")

    private var style by stringPref(key = "STYLE", default = Style.YELLOW.name)
    private var uuid by stringPref(key = "UUID", default = "")

    override fun setToken(value: String) {
        authToken = value
    }

    override fun getToken(): String = authToken

    override fun getDefaultStyle(): String = style

    override fun setDefaultStyle(value: String) {
        style = value
    }

    override fun getUUID() = if (uuid.isNullOrEmpty()) {
        uuid = UUID.randomUUID().toString()
        uuid
    } else {
        uuid
    }
}