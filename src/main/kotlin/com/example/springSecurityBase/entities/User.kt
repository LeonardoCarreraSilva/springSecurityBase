package com.example.springSecurityBase.entities

import jakarta.persistence.*
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
)