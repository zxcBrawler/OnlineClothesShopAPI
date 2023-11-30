package com.example.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "photos_of_clothes")
data class PhotosOfClothes (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    //DONE
    @ManyToOne
    val clothesId: Clothes = Clothes(),
    //DONE
    @ManyToOne
    val clothesPhoto: ClothesPhoto = ClothesPhoto(),
)