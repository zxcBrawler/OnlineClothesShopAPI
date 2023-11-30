package com.example.demo.models

import jakarta.persistence.*
import jakarta.persistence.criteria.Order


@Entity
@Table(name = "order_comp")
data class OrderComposition(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderCompId: Long = 0,

    //DONE
    @ManyToOne
    val clothesComp: Clothes = Clothes(),

    //DONE
    @ManyToOne
    val orderId: Orders = Orders(),

    val quantity: Int = 0,
)
