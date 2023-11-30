package com.example.demo.repositories

import com.example.demo.models.ClothesColors
import com.example.demo.models.ClothesSizeClothes
import org.springframework.data.repository.CrudRepository

interface ClothesSizeClothesRepository: CrudRepository<ClothesSizeClothes, Long> {

    fun findAllByClothesIdClothes (id : Long) : List<ClothesSizeClothes>
}