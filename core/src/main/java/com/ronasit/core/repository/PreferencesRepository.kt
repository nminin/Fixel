package com.ronasit.core.repository

interface PreferencesRepository {

    fun setToken(value: String)

    fun getToken(): String

}