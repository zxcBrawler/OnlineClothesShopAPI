package com.example.demo.repositories

import com.example.demo.models.TypeClothes
import org.springframework.data.repository.CrudRepository

interface TypeClothesRepository: CrudRepository<TypeClothes, Long> {}