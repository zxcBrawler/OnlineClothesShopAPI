package com.example.demo.repositories

import com.example.demo.models.Clothes
import org.springframework.data.repository.CrudRepository

interface ClothesRepository: CrudRepository<Clothes, Long> {}