package org.hxt.logofmoney.utils

enum class Authority {
    Admin,
    Customer,
    Staff
}

object Endpoints {


    val PUBLIC_GET_ENDPOINS = arrayListOf(
        "/index",
        "/index/**",

        "/type",
        "/type/**",

        "/record",
        "/record/**",

        )

    val PUBLIC_POST_ENDPOINS = arrayListOf(

        "/login",
        "/login/**",

        "/signup",
        "/signup/**"

        )


    val ADMIN_GET_ENDPOINS = arrayListOf(
        "/index1",
        "/index1/**",
    )

    val ADMIN_POST_ENDPOINS = arrayListOf(
        "/type",
        "/type/**",

        "/record",
        "/record/**",

    )
}