package com.example.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "user_address")
data class UserAddress(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userAddressId: Long = 0,

    //DONE
    @ManyToOne
    val user: User = User(),

    //DONE
    @ManyToOne
    val address: Address = Address(),
)
