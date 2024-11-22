package com.example.springSecurityBase.dto

data class LoginResponse (
    val accessToken: String,
    val expiresIn: Long
)
