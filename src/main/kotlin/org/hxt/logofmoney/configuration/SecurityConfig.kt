package org.hxt.logofmoney.configuration

import org.hxt.logofmoney.entity.repository.UserRepository
import org.hxt.logofmoney.utils.Authority
import org.hxt.logofmoney.utils.Endpoints
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests {
            it
                .requestMatchers(HttpMethod.POST, *Endpoints.PUBLIC_POST_ENDPOINS.toTypedArray())
                .permitAll()
                .requestMatchers(HttpMethod.GET, *Endpoints.PUBLIC_GET_ENDPOINS.toTypedArray())
                .permitAll()
                .requestMatchers(HttpMethod.GET, *Endpoints.ADMIN_GET_ENDPOINS.toTypedArray())
                .hasAuthority(Authority.Admin.name)
        }





        http.cors { cors ->
            cors.configurationSource { request ->
                val corsConfig = CorsConfiguration()
                corsConfig.addAllowedOrigin("*")
                corsConfig.allowedMethods = mutableListOf(
                    "GET",
                    "POST",
                    "PUT",
                    "DELETE"
                )
                corsConfig.addAllowedHeader("*")
                corsConfig
            }
        }

        http.sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        http.httpBasic(Customizer.withDefaults())
        http.csrf { csrf -> csrf.disable() }


        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    fun authenticationManager(
        userDetailsService: UserDetailsService,
        passwordEncoder: PasswordEncoder
    ): AuthenticationManager {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder)

        return ProviderManager(authenticationProvider)
    }


    @Autowired
    private lateinit var userRepository: UserRepository

    @Bean
    fun userDetailsService(): UserDetailsService {
        val all = userRepository.findAll()
        val list = ArrayList<UserDetails>()
        for (user in all) {
            val userDetails: UserDetails = User.builder()
                .username(user.name)
                .password(passwordEncoder().encode(user.password))
                .authorities(*user.authority.split(" ").toTypedArray())
                .build()



            list.add(
                userDetails
            )
        }
//        list.add(
//            User.builder()
//                .username("name")
//                .password(passwordEncoder().encode("password"))
//                .authorities(Authority.Admin.name)
//                .build()
//        )
        println(all)
        println(list)
        return InMemoryUserDetailsManager(list)
    }

//    @Autowired
//    fun configure(builder: AuthenticationManagerBuilder) {
//        builder.eraseCredentials(false)
//    }


}