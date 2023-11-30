package com.example.demo.repositories

import com.example.demo.models.SizeClothes
import org.springframework.data.repository.CrudRepository

interface SizeClothesRepository: CrudRepository<SizeClothes, Long> {
}