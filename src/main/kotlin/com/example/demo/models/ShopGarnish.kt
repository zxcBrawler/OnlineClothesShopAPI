package com.example.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "shop_clothes")
data class ShopGarnish(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val shopGarnishId: Long = 0,

    //DONE
    @ManyToOne
    val sizeClothesGarnish: ClothesSizeClothes = ClothesSizeClothes(),

    //DONE
    @ManyToOne
    val colorClothesGarnish: ClothesColors = ClothesColors(),

    //DONE
    @ManyToOne
    val shopAddressesGarnish: ShopAddresses = ShopAddresses(),

    val quantity: Int = 0,
)
