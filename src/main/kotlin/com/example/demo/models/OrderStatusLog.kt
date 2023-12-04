package com.example.demo.models

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "orderStatusLog")
data class OrderStatusLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne
    val status: StatusOrder,

    @ManyToOne
    val orders: Orders,

    val timestamp: String
)
