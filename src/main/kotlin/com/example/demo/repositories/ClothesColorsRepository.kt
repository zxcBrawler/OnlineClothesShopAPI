package com.example.demo.repositories

import com.example.demo.models.ClothesColors
import org.springframework.data.repository.CrudRepository

interface ClothesColorsRepository: CrudRepository<ClothesColors, Long> {

    fun findAllByClothesIdClothes (id : Long) : List<ClothesColors>
}