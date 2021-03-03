package com.ronasit.core.repository

interface PreferencesRepository {

    fun setToken(value: String)

    fun getToken(): String

    fun getDefaultStyle(): String

    fun setDefaultStyle(value: String)

    fun getUUID(): String

}