package com.example.springSecurityBase.entities

import com.example.springSecurityBase.dto.LoginRequest
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID

@Entity
@Table(name = "tb_users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    val id: UUID,
    @Column(unique = true)
    val name: String,
    val password:String,
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "tb_users_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles:Set<Role>
) {
    fun isLoginCorrect(loginRequest: LoginRequest, passwordEncoder: PasswordEncoder): Boolean {
        return passwordEncoder.matches(loginRequest.password, this.password)
    }
}