package com.example.springSecurityBase.entities

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(name = "tb_tweets")
data class Tweet(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tweet_id")
    val id: Long,
    val user: User,
    val content: String,
    @CreationTimestamp
    val creationTimesTempo: Instant
)
