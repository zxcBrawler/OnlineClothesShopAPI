package com.example.demo.models

import jakarta.persistence.*


@Entity
@Table(name = "order_comp")
data class OrderComposition(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderCompId: Long = 0,

    //DONE
    @ManyToOne
    var clothesComp: Clothes = Clothes(),
    @ManyToOne
    var sizeClothes : SizeClothes = SizeClothes(),
    @ManyToOne
    var colorClothes: Color = Color(),

    //DONE
    @ManyToOne(cascade = [CascadeType.ALL])
    var orderId: Order = Order(),

    var quantity: Int = 0,
)
