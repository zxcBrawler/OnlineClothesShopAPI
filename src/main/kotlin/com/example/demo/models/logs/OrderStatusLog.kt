package com.example.demo.models.logs

import com.example.demo.models.Order
import com.example.demo.models.StatusOrder
import jakarta.persistence.*

@Entity
@Table(name = "orderStatusLog")
data class OrderStatusLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    var status: StatusOrder = StatusOrder(),

    @ManyToOne
    var orders: Order = Order(),

    var timestamp: String = ""
)
