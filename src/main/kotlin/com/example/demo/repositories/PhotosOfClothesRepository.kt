package com.example.demo.repositories

import com.example.demo.models.PhotosOfClothes
import org.springframework.data.repository.CrudRepository

interface PhotosOfClothesRepository: CrudRepository<PhotosOfClothes, Long> {

    fun getPhotosOfClothesByClothesId_IdClothes (id : Long) : List<PhotosOfClothes>
}