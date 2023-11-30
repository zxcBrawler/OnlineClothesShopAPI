package com.example.demo.repositories

import com.example.demo.models.ClothesPhoto
import org.springframework.data.repository.CrudRepository

interface ClothesPhotoRepository : CrudRepository<ClothesPhoto, Long> {
}