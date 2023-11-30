package com.example.demo.controllers

import com.example.demo.models.Clothes
import com.example.demo.repositories.ClothesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clothes")
class ClothesController(@Autowired private val clothesRepository: ClothesRepository)  {
    @GetMapping("")
    fun getAllClothes(): List<Clothes> =
        clothesRepository.findAll().toList()

    @PostMapping("")
    fun createClothes(@RequestBody clothes: Clothes): ResponseEntity<Clothes> {
        val createdClothes = clothesRepository.save(clothes)
        return ResponseEntity(createdClothes, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getClothesById(@PathVariable("id") clothesId: Long): ResponseEntity<Clothes> {
        val clothes = clothesRepository.findById(clothesId).orElse(null)
        return if (clothes != null) ResponseEntity(clothes, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateClothesById(@PathVariable("id") clothesId: Long, @RequestBody clothes: Clothes): ResponseEntity<Clothes> {

        val existingClothes = clothesRepository.findById(clothesId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedClothes = existingClothes.copy(
            nameClothes = clothes.nameClothes,
            priceClothes = clothes.priceClothes,
            barcode = clothes.barcode,
            typeClothes = clothes.typeClothes)
        clothesRepository.save(updatedClothes)
        return ResponseEntity(updatedClothes, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteClothesById(@PathVariable("id") clothesId: Long): ResponseEntity<Clothes> {
        if (!clothesRepository.existsById(clothesId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        clothesRepository.deleteById(clothesId.toLong())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}