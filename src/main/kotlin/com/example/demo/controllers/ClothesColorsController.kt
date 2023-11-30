package com.example.demo.controllers

import com.example.demo.models.ClothesColors
import com.example.demo.repositories.ClothesColorsRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clothesColors")
class ClothesColorsController (@Autowired private val clothesColorsRepository: ClothesColorsRepository)  {
    @GetMapping("")
    fun getAllClothesColors(): List<ClothesColors> =
        clothesColorsRepository.findAll().toList()

    @PostMapping("")
    fun createClothesColors(@RequestBody clothesColors: ClothesColors): ResponseEntity<ClothesColors> {
        val createdClothesColors = clothesColorsRepository.save(clothesColors)
        return ResponseEntity(createdClothesColors, HttpStatus.CREATED)
    }

    @GetMapping("/{clothesId}")
    fun getClothesColorsById(@PathVariable("clothesId") clothesId: Long): ResponseEntity<Any> {

        val clothesColor = clothesColorsRepository.findAllByClothesIdClothes(clothesId)
        return ResponseEntity(clothesColor, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteClothesColorsById(@PathVariable("id") clothesColorsId: Long): ResponseEntity<ClothesColors> {
        if (!clothesColorsRepository.existsById(clothesColorsId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        clothesColorsRepository.deleteById(clothesColorsId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}