package org.hxt.logofmoney.controller

import org.hxt.logofmoney.entity.repository.UserRepository
import org.hxt.logofmoney.utils.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    val authenticationManager: AuthenticationManager,
    val inMemoryUserDetailsManager: InMemoryUserDetailsManager,
    val passwordEncoder: PasswordEncoder
) {

    @Autowired
    private lateinit var repository: UserRepository

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        println("login")
        val authenticationRequest =
            UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.username, loginRequest.password
            )
        val authenticationResponse =
            authenticationManager.authenticate(authenticationRequest)
        return ResponseEntity.ok(authenticationResponse.authorities)
    }


    @PostMapping("/signup")
    fun signup(@RequestBody request: SignRequest): ResponseEntity<Any> {
        println("signup")
        val u = Util.create(request)
        val u1 = repository.findAll()
        for(i in u1) {
            if(i.name.equals(request.username)) {
                return ResponseEntity.badRequest().body("Đã tồn tại")
            }
        }

        repository.save(u)
        inMemoryUserDetailsManager.createUser(Util.create(u, passwordEncoder))

        return ResponseEntity.ok("ok")
    }


    data class LoginRequest(val username: String, val password: String)
    data class SignRequest(val username: String, val password: String, val authority: String)
}