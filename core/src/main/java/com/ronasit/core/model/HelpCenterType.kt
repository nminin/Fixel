package com.ronasit.core.model

enum class HelpCenterType(val domain: String) {
    HOW_TO_PAY("how_to_pay"),
    RETURNS_POLICY("returns_policy"),
    PRIVACY_POLICY("privacy_policy"),
    TERMS_AND_CONDITIONS("terms_and_conditions"),
    ABOUT_US("about_us");

    companion object {
        fun getAll() = listOf(
            HOW_TO_PAY,
            RETURNS_POLICY,
            PRIVACY_POLICY,
            TERMS_AND_CONDITIONS,
            ABOUT_US
        )
        fun getFromString(domain: String)  = getAll().firstOrNull {
            it.domain == domain
        }
    }
}
