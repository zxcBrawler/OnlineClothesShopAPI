package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "typeClothes")
data class TypeClothes(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idType: Long = 0,
    val nameType: String = "",
    //DONE
    @ManyToOne
    val categoryClothes: CategoryClothes = CategoryClothes(),

    @JsonIgnore
    @OneToMany(mappedBy = "typeClothes")
    var clothesList : List<Clothes> = arrayListOf()
)
