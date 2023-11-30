package com.example.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "statusOrder")
data class StatusOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idStatus: Long = 0,
    @Column(unique = true)
    val nameStatus: String = "",
)
