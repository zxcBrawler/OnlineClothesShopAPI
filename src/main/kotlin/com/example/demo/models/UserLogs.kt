package com.example.demo.models

import jakarta.persistence.*
import java.sql.Timestamp


@Entity
@Table(name = "userLogs")
data class UserLogs(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne
    var user: User,

    var timestamp: String,

    var description: String,
)