package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "clothes_colors")
data class ClothesColors(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne
    val clothes: Clothes = Clothes(),

    @ManyToOne
    val colors: Color = Color(),

    @JsonIgnore
    @OneToMany(mappedBy = "colorClothesCart")
    var cart : List<Cart> = arrayListOf(),
    @JsonIgnore
    @OneToMany(mappedBy = "colorClothesGarnish")
    var colorClothesGarnish : List<ShopGarnish> = arrayListOf()
)
