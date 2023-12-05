package com.example.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "user_order")
data class UserOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userOrderId: Long = 0,

    @ManyToOne
    var user: User = User(),

    @ManyToOne
    var orders: Order = Order(),
)
