package com.example.springSecurityBase.repositories

import com.example.springSecurityBase.entities.Tweet
import com.example.springSecurityBase.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TweetRepository : JpaRepository<Tweet, Long> {
}