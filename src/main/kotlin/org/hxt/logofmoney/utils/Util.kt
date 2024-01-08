package org.hxt.logofmoney.utils

import org.springframework.security.core.userdetails.User
import org.hxt.logofmoney.controller.LoginController
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.hxt.logofmoney.entity.table.User as User1
import java.util.Date

object Util {

    private lateinit var passwordEncoder: PasswordEncoder

    fun create(user: User1, passwordEncoder: PasswordEncoder): UserDetails {
        return User.builder()
            .username(user.name)
            .password(passwordEncoder.encode(user.password))
            .authorities(*user.authority.split(" ").toTypedArray())
            .build()
    }

    fun create(u: User1) = LoginController.LoginRequest(u.name, u.password)
    fun create(u: LoginController.SignRequest) = User1(Date().time, u.username, u.password, u.authority)

}