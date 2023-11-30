package com.example.demo.controllers

import com.example.demo.models.ClothesSizeClothes
import com.example.demo.models.SizeClothes
import com.example.demo.repositories.ClothesSizeClothesRepository
import com.example.demo.repositories.SizeClothesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/clothesSizeClothes")
class ClothesSizeClothesController (@Autowired private val clothesSizeClothesRepository: ClothesSizeClothesRepository)  {
    @GetMapping("")
    fun getAllClothesSizeClothes(): List<ClothesSizeClothes> =
        clothesSizeClothesRepository.findAll().toList()

    @PostMapping("")
    fun createClothesSizeClothes(@RequestBody sizeClothes: ClothesSizeClothes): ResponseEntity<ClothesSizeClothes> {
        val createdClothesSizeClothes = clothesSizeClothesRepository.save(sizeClothes)
        return ResponseEntity(createdClothesSizeClothes, HttpStatus.CREATED)
    }

    @GetMapping("/{clothesId}")
    fun getClothesSizeClothesById(@PathVariable("clothesId") clothesId: Long): ResponseEntity<Any> {
        val sizeClothes = clothesSizeClothesRepository.findAllByClothesIdClothes(clothesId)
        return ResponseEntity(sizeClothes, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteClothesSizeClothesById(@PathVariable("id") clothesSizeClothesId: Long): ResponseEntity<ClothesSizeClothes> {
        if (!clothesSizeClothesRepository.existsById(clothesSizeClothesId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        clothesSizeClothesRepository.deleteById(clothesSizeClothesId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}