package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "sizeClothes")
data class SizeClothes(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    @Column(unique = true)
    val nameSize: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "sizeClothes")
    val sizeClothesList: List<ClothesSizeClothes> = arrayListOf()
)
