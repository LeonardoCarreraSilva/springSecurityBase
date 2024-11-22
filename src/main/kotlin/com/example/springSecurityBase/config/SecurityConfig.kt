package com.example.springSecurityBase.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests {
            it.anyRequest().authenticated()
        }
        http.csrf { csrf -> csrf.disable() }
        http.oauth2ResourceServer { it -> it.jwt(Customizer.withDefaults()) }
        http.sessionManagement{ it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        return http.build()
    }
}