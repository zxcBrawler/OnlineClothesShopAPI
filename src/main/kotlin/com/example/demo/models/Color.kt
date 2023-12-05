package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "color")
data class Color(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val colorId: Long = 0,
    @Column(unique = true)
    val nameColor: String = "",
    @Column(unique = true)
    val hex: String = "",
    @JsonIgnore
    @OneToMany(mappedBy = "colors")
    var colorsList : List<ClothesColors> = arrayListOf()
)
