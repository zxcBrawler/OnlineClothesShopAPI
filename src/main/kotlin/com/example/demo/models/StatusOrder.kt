package com.example.demo.models

import com.example.demo.models.logs.OrderStatusLog
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "statusOrder")
data class StatusOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idStatus: Long = 0,
    @Column(unique = true)
    val nameStatus: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "status")
    val orderStatusLogStatus : List<OrderStatusLog> = arrayListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "currentStatus")
    val orderStatus : List<Order> = arrayListOf()
)
