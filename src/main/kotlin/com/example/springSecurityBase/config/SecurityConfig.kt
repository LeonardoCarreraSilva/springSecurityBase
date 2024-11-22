package com.example.springSecurityBase.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain
import java.security.interfaces.RSAKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Value("\${jwt.public.key}")
    lateinit var publicKey: RSAPublicKey

    @Value("\${jwt.private.key}")
    lateinit var privateKey: RSAPrivateKey

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests {
            it.requestMatchers(HttpMethod.POST,"/login").permitAll()
            it.anyRequest().authenticated()
        }
        http.csrf { csrf -> csrf.disable() }
        http.oauth2ResourceServer { it -> it.jwt(Customizer.withDefaults()) }
        http.sessionManagement{ it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        return http.build()
    }

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwk = com.nimbusds.jose.jwk.RSAKey.Builder(publicKey).privateKey(privateKey).build()
        return NimbusJwtEncoder(com.nimbusds.jose.jwk.source.ImmutableJWKSet(com.nimbusds.jose.jwk.JWKSet(jwk)))
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withPublicKey(publicKey).build()
    }

    @Bean
    fun bcryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}