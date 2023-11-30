package com.example.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "type_delivery")
data class TypeDelivery (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    @Column(unique = true)
    var nameType : String = "",

)