package com.example.demo.repositories

import com.example.demo.models.CategoryClothes
import org.springframework.data.repository.CrudRepository

interface CategoryClothesRepository : CrudRepository<CategoryClothes, Long> {
}