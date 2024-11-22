package com.example.springSecurityBase.repositories

import com.example.springSecurityBase.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findByName(userName: String): Optional<User>
}