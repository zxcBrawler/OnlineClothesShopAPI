package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "clothes_sizes_clothes")
data class ClothesSizeClothes(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne
    val clothes: Clothes = Clothes(),

    @ManyToOne
    val sizeClothes: SizeClothes = SizeClothes(),

    @JsonIgnore
    @OneToMany(mappedBy = "sizeClothes")
    var cart : List<Cart> = arrayListOf(),
    @JsonIgnore
    @OneToMany(mappedBy = "sizeClothesGarnish")
    var sizeClothesGarnish : List<ShopGarnish> = arrayListOf()


)
