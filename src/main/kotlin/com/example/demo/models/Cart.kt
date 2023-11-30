package com.example.demo.models

import jakarta.persistence.*


@Entity
@Table(name = "cart")
data class Cart(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    var user : User = User(),

    @ManyToOne
    var sizeClothes: ClothesSizeClothes = ClothesSizeClothes(),

    @ManyToOne
    var colorClothesCart: ClothesColors = ClothesColors(),

    var quantity: Int = 0,
)
