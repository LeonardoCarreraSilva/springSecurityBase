package com.example.springSecurityBase.controller

import com.example.springSecurityBase.dto.LoginRequest
import com.example.springSecurityBase.dto.LoginResponse
import com.example.springSecurityBase.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class TokenController(
    private val jwtEncoder: JwtEncoder,
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val user = userRepository.findByName(loginRequest.username)

        if (user.isEmpty || user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)) {
            throw BadCredentialsException("Usuario ou senha invalido")
        }
        val expiresIn = 300L
        val now = Instant.now()

        val cleais = JwtClaimsSet.builder().issuer("MyAppName").subject(user.get().id.toString())
            .expiresAt(now.plusSeconds(expiresIn)).issuedAt(now).build()

        val jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(cleais)).tokenValue

        return ResponseEntity.ok(LoginResponse(jwtValue, expiresIn))
    }
}