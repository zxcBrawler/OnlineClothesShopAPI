package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "categoryClothes")
data class CategoryClothes (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    @Column(unique = true)
    val nameCategory: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "categoryClothes")
    var typeList : List<TypeClothes> = arrayListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "gender")
    var userGender: List<User> = arrayListOf(),

)