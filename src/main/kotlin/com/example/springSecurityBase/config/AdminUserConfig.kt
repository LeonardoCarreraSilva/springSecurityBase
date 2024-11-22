package com.example.springSecurityBase.config

import com.example.springSecurityBase.entities.Role
import com.example.springSecurityBase.entities.User
import com.example.springSecurityBase.repositories.RoleRepository
import com.example.springSecurityBase.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@Configuration
class AdminUserConfig(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : CommandLineRunner {
    @Transactional
    override fun run(vararg args: String?) {
        val roleAdmin = roleRepository.findById(Role.Values.ADMIN.getRoleId())
        val userAdmin = userRepository.findByName("admin")
        if (userAdmin.isEmpty) {
            userRepository.save(User(
                id = UUID.randomUUID(),
                name = "admin",
                password = bCryptPasswordEncoder.encode("admin"),
                roles = setOf(roleAdmin.get())
            ))
        }
    }
}