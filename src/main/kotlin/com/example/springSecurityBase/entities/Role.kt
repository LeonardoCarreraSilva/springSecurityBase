package com.example.springSecurityBase.entities

import jakarta.persistence.*

@Entity
@Table(name = "tb_roles")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    val id: Long,
    val name:String
    )

enum class Values(val id: Long){
    ADMIN(1L),
    BASIC(2L);

    fun getRoleId(): Long {
        return this.id
    }
}
